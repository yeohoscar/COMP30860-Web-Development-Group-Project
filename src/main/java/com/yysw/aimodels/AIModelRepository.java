package com.yysw.aimodels;

import com.yysw.cart.ShoppingCartItem;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AIModelRepository extends JpaRepository<AIModel, Long> {
    AIModel findAIModelByModelName(String modelName);

    AIModel findAIModelById(Long id);

    ShoppingCartItem findAiModelById(long id) ;
}