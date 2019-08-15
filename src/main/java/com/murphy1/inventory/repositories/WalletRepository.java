package com.murphy1.inventory.repositories;

import com.murphy1.inventory.model.Wallet;
import org.springframework.data.repository.CrudRepository;

public interface WalletRepository extends CrudRepository<Wallet, Long> {
}
