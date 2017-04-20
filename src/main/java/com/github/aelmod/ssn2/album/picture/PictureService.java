package com.github.aelmod.ssn2.album.picture;

import com.github.aelmod.ssn2.user.BadUserBehaviorException;
import com.github.aelmod.ssn2.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.persistence.EntityNotFoundException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.io.File.separator;

@Service
public class PictureService {

    @Value("${user.picture.path}")
    private String PATH;

    @Value("${api.user.picture.path}")
    private String apiUserPicturePath;

    private File dir;

    private final String IMAGE_FORMAT = "png";

    private final PictureRepository pictureRepository;

    @Autowired
    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @PostConstruct
    public void init() {
        dir = new File(PATH);
        if (!dir.exists()) dir.mkdirs();
    }

    @Transactional
    public void add(Picture picture) {
        if (Objects.nonNull(picture.getId()))
            throw new IllegalStateException("You cannot create picture with specified id");
        pictureRepository.persist(picture);
    }

    @Transactional
    public Picture getByPk(Integer id) {
        Optional<Picture> pictureOptional = pictureRepository.findOneByPk(id);
        pictureOptional.ifPresent(picture -> picture.setFullPath(apiUserPicturePath + picture.getId() + "." + IMAGE_FORMAT));
        return pictureOptional.orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public List<Picture> getBy(PictureSpecification pictureSpecification) {
        List<Picture> pictures = pictureRepository.findBy(pictureSpecification);
        pictures.forEach(picture -> picture.setFullPath(PATH + picture.getName()));
        return pictures;
    }

    @Transactional
    public void store(MultipartFile[] pictures, User user) {
        for (MultipartFile picture : pictures) {
            Picture pictureEntity = new Picture();
            pictureEntity.setTimeOfAddition(new Date());
            pictureEntity.setUser(user);
            add(pictureEntity);
            savePicture(picture, pictureEntity.getId().toString());
        }
    }

    private void savePicture(MultipartFile picture, String name) {
        try {
            BufferedImage bufferedImage = ImageIO.read(picture.getInputStream());
            File serverFile = new File(dir.getAbsolutePath() + separator + name + "." + IMAGE_FORMAT);
            ImageIO.write(bufferedImage, IMAGE_FORMAT, serverFile);
        } catch (Exception e) {
            throw new BadUserBehaviorException("You failed to upload " + name + " => " + e.getMessage());
        }
    }

//    private String getContainerName(MultipartFile picture) {
//        String originalFilename = picture.getOriginalFilename();
//        return originalFilename.substring(originalFilename.lastIndexOf("."));
//    }
}
