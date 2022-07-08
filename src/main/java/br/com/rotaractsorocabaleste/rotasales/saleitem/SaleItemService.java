package br.com.rotaractsorocabaleste.rotasales.saleitem;

import java.util.Set;
import java.util.UUID;

public interface SaleItemService {

    Set<SaleItem> saveAll(final Set<SaleItem> items);

    void deleteAllByIdInBatch(final Set<UUID> ids);

}
