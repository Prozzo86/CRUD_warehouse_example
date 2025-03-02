package algebra.spring_boot.category;

import algebra.spring_boot.article.ArticleRepository;
import org.apache.logging.log4j.util.InternalException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final ArticleRepository articleRepository;

    private final CategoryRepository categoryRepository;

    /*

    Constructor injection jasno pokazuje koje su zavisnosti klase. Kada netko pogleda konstruktor, odmah vidi koje su komponente potrebne za rad klase.

    S druge strane, ako koristim field injection (@Autowired), zavisnosti su skrivene i nije odmah jasno što je sve potrebno za ispravno funkcioniranje klase.

    */

    public CategoryService(ArticleRepository articleRepository, CategoryRepository categoryRepository) {
        this.articleRepository = articleRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Category> fetchAll() {
        return categoryRepository.fetchAll();
    }

    public Optional<Category> findById(Integer id) {
        return categoryRepository.findById(id);
    }

    public Category create(CreateCategoryDto dto) {
        Category category = new Category(null, dto.getName(), dto.getDescription());
        return categoryRepository.create(category);
    }

    public Category update(Integer id, UpdateCategoryDto dto) {
        Optional<Category> category = categoryRepository.findById(id);

        if (category.isEmpty()) {
            throw new InternalException("Category not found");
        }

        Category categoryForUpdate = category.get();
        categoryForUpdate.setName(dto.getName());
        categoryForUpdate.setDescription(dto.getDescription());

        return categoryRepository.update(categoryForUpdate);
    }


}
