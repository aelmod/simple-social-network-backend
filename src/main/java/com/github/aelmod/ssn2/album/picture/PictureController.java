package com.github.aelmod.ssn2.album.picture;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.aelmod.ssn2.miscellaneous.UserIdForm;
import com.github.aelmod.ssn2.security.CurrentUser;
import com.github.aelmod.ssn2.user.User;
import com.github.aelmod.ssn2.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/pictures")
public class PictureController {

    private final PictureService pictureService;

    private final UserService userService;

    @Autowired
    public PictureController(PictureService pictureService, UserService userService) {
        this.pictureService = pictureService;
        this.userService = userService;
    }

    @PostMapping
    public void handlePictureUpload(@RequestPart("picture") MultipartFile[] pictures, @CurrentUser User user) {
        pictureService.store(pictures, user);
    }

    @PostMapping("last")
    @JsonView(Picture.AllPrimitivesView.class)
    public Picture getLastPicture(@RequestBody @Valid UserIdForm userIdForm) {
        User user = userService.getByPk(userIdForm.getUserId());
        List<Picture> pictures = user.getPictures();
        if (pictures.isEmpty()) throw new EntityNotFoundException();
        if (pictures.size() == 1) return pictureService.getByPk(pictures.get(0).getId());
        return pictureService.getByPk(pictures.get(pictures.size() - 1).getId());
    }
}
