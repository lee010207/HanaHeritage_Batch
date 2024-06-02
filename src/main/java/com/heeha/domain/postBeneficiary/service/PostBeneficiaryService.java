package com.heeha.domain.postBeneficiary.service;

import com.heeha.domain.postBeneficiary.entity.PostBeneficiary;
import com.heeha.domain.postBeneficiary.repository.PostBeneficiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostBeneficiaryService{
//implements BaseService<PostBeneficiary, Long>
    final PostBeneficiaryRepository repository;

    //@Override
    public PostBeneficiary insert(PostBeneficiary entity) {
        return repository.save(entity);
    }

    //@Override
    public Boolean delete(Long id) {
        repository.deleteById(id);
        return true;
    }

   //@Override
    public PostBeneficiary update(PostBeneficiary entity) {
        return repository.save(entity);
    }

    //@Override
    public Optional<PostBeneficiary> get(Long id) {
        return repository.findById(id);
    }

    //@Override
    public List<PostBeneficiary> getAll() {
        return repository.findAll();
    }
}
