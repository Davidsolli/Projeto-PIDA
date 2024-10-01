package com.david.amazonas.services;

import com.david.amazonas.domains.users.Seller;
import com.david.amazonas.dtos.users.SellerDTO;
import com.david.amazonas.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Transactional
    public SellerDTO insert(SellerDTO sellerDTO) {

        Seller seller = new Seller();

        copyDtoToEntity(seller, sellerDTO);

        seller = sellerRepository.save(seller);

        return new SellerDTO(seller);
    }

    @Transactional(readOnly = true)
    public SellerDTO findById(Long id) {
        Seller seller = sellerRepository.findById(id).orElseThrow();
        return new SellerDTO(seller);
    }

    @Transactional
    public SellerDTO update(Long id, SellerDTO sellerDTO) {
        Seller seller = sellerRepository.getReferenceById(id);
        copyDtoToEntity(seller, sellerDTO);
        seller = sellerRepository.save(seller);
        return new SellerDTO(seller);
    }

    public void copyDtoToEntity(Seller seller, SellerDTO sellerDTO) {
        seller.setCnpj(sellerDTO.getCnpj());
        seller.setEmail(sellerDTO.getEmail());
        seller.setBusinessName(sellerDTO.getBusinessName());
        seller.setPhoneNumber(sellerDTO.getPhoneNumber());
    }
}
