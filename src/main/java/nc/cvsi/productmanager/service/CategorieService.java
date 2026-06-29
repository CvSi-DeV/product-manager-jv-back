package nc.cvsi.productmanager.service;

import org.springframework.stereotype.Service;

import nc.cvsi.productmanager.exception.CategorieNonTrouveException;
import nc.cvsi.productmanager.model.Categorie;
import nc.cvsi.productmanager.repository.CategorieRepository;

@Service
public class CategorieService {

    private final CategorieRepository categorieRepository;

    public CategorieService(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    public Categorie findByCategorie(String categorie) {
        return this.categorieRepository.findByNom(categorie)
                .orElseThrow(() -> new CategorieNonTrouveException(("Catégorie non trouvée")));
    }
}
