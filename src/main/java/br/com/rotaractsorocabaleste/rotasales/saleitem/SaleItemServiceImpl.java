package br.com.rotaractsorocabaleste.rotasales.saleitem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SaleItemServiceImpl implements SaleItemService {

    private final SaleItemRepository saleItemRepository;

    @Override
    public Set<SaleItem> saveAll(final Set<SaleItem> items) {
        return new HashSet<>(saleItemRepository.saveAll(items));
    }

    @Override
    public void deleteAllByIdInBatch(Set<UUID> ids) {
        saleItemRepository.deleteAllByIdInBatch(ids);
    }

}
