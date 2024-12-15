package cat.tecnocampus.stickeralbum.persistence;

import cat.tecnocampus.stickeralbum.domain.Collector;
import cat.tecnocampus.stickeralbum.domain.HasSticker;
import cat.tecnocampus.stickeralbum.domain.HasStickerPK;
import cat.tecnocampus.stickeralbum.domain.Sticker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface HasStickerRepository extends JpaRepository<HasSticker, HasStickerPK> {

    @Query("""
        SELECT hs FROM HasSticker hs 
        JOIN Collection c on c.collectionPK = hs.hasStickerPK.collectionPK
        WHERE c.collector = :owner AND hs.sticker = :sticker
        """)
    Optional<HasSticker> findByOwnerAndSticker(Collector owner, Sticker sticker);


}
