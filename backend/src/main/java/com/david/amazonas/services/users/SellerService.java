package com.david.amazonas.services.users;

import com.david.amazonas.domains.users.User;
import com.david.amazonas.dtos.users.SellerListDTO;
import com.david.amazonas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SellerService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<SellerListDTO> findAll(Pageable pageable) {
        Page<User> result = userRepository.searchSellers(pageable);
        return  result.map(SellerListDTO::new);
    }
}
