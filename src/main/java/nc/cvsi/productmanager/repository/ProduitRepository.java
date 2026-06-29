package nc.cvsi.productmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import nc.cvsi.productmanager.model.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
    List<Produit> findByCategorieNom(String categorieName);

    List<Produit> findByPourcentagePromoIsNotNull();

}
