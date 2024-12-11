package cat.tecnocampus.stickeralbum.application;

import cat.tecnocampus.stickeralbum.application.inputDTOs.AlbumCommand;
import cat.tecnocampus.stickeralbum.application.mapper.AlbumMapper;
import cat.tecnocampus.stickeralbum.application.outputDTOs.AlbumDTO;
import cat.tecnocampus.stickeralbum.domain.Album;
import cat.tecnocampus.stickeralbum.domain.Collector;
import cat.tecnocampus.stickeralbum.persistence.AlbumRepository;
import cat.tecnocampus.stickeralbum.persistence.CollectorRepository;
import cat.tecnocampus.stickeralbum.persistence.SectionRepository;
import cat.tecnocampus.stickeralbum.persistence.StickerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final SectionRepository sectionRepository;
    private final StickerRepository stickerRepository;
    private final CollectorRepository collectorRepository;

    public AlbumService(AlbumRepository albumRepository, SectionRepository sectionRepository, StickerRepository stickerRepository, CollectorRepository collectorRepository) {
        this.albumRepository = albumRepository;
        this.sectionRepository = sectionRepository;
        this.stickerRepository = stickerRepository;
        this.collectorRepository = collectorRepository;
    }

    public List<Album> getDomainAlbums() {
        return albumRepository.findAll();
    }

    public AlbumDTO getAlbum(Long albumId) {
        Album album = albumRepository.findById(albumId).orElseThrow(() -> new RuntimeException("Album not found"));
        AlbumDTO albumDTO = AlbumMapper.mapAlbumDomainToDTO(album);
        return albumDTO;
    }

    public void createAlbum(AlbumCommand album) {
        Collector owner = collectorRepository.findById(album.ownerId()).orElseThrow(() -> new RuntimeException("Owner not found"));
        Album domainAlbum = AlbumMapper.mapAlbumDTOtoDomain(album);
        domainAlbum.setOwner(owner);
        albumRepository.save(domainAlbum);
    }
}