package com.david.amazonas.services;

import com.david.amazonas.domains.users.Buyer;
import com.david.amazonas.dtos.users.BuyerDTO;
import com.david.amazonas.repositories.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BuyerService {

    @Autowired
    private BuyerRepository buyerRepository;

    @Transactional
    public BuyerDTO insert(BuyerDTO buyerDTO) {
        Buyer buyer = new Buyer();
        copyDtoToEntity(buyer, buyerDTO);
        buyer = buyerRepository.save(buyer);
        return new BuyerDTO(buyer);
    }

    @Transactional(readOnly = true)
    public BuyerDTO findById(Long id) {
        Buyer buyer = buyerRepository.findById(id).orElseThrow();
        return new BuyerDTO(buyer);
    }

    @Transactional
    public BuyerDTO update(Long id, BuyerDTO buyerDTO) {
        Buyer buyer = buyerRepository.getReferenceById(id);
        copyDtoToEntity(buyer, buyerDTO);
        buyer = buyerRepository.save(buyer);
        return new BuyerDTO(buyer);
    }

    @Transactional
    public void delete(Long id) {
        buyerRepository.deleteById(id);
    }

    private void copyDtoToEntity(Buyer buyer, BuyerDTO buyerDTO) {
        buyer.setEmail(buyerDTO.getEmail());
        buyer.setFirstName(buyerDTO.getFirstName());
        buyer.setLastName(buyerDTO.getLastName());
        buyer.setPhoneNumber(buyerDTO.getPhoneNumber());
        buyer.setAge(buyerDTO.getAge());
        buyer.setCpf(buyerDTO.getCpf());
        buyer.setAddress(buyerDTO.getAddress());
    }
}
