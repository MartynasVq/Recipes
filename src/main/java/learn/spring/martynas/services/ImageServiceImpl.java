package learn.spring.martynas.services;

import learn.spring.martynas.domain.Recipe;
import learn.spring.martynas.repositories.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveImageFile(Long valueOf, MultipartFile multipartFile) {
    try {
        Recipe recipe = recipeRepository.findById(valueOf).get();
        Byte[] byteObjects = new Byte[multipartFile.getBytes().length];

        int i = 0;

        for(byte b : multipartFile.getBytes())
            byteObjects[i++] = b;

        recipe.setImage(byteObjects);
        recipeRepository.save(recipe);

    } catch (IOException e) {
        e.printStackTrace();
    }
    }
}
