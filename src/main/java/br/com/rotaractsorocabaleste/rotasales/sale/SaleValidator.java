package br.com.rotaractsorocabaleste.rotasales.sale;

import br.com.rotaractsorocabaleste.rotasales.event.EventService;
import br.com.rotaractsorocabaleste.rotasales.item.Item;
import br.com.rotaractsorocabaleste.rotasales.item.ItemService;
import br.com.rotaractsorocabaleste.rotasales.saleitem.SaleItem;
import br.com.rotaractsorocabaleste.rotasales.common.exception.BusinessException;
import br.com.rotaractsorocabaleste.rotasales.common.exception.ErrorType;
import br.com.rotaractsorocabaleste.rotasales.common.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class SaleValidator implements Validator<Sale> {

    private final EventService eventService;
    private final ItemService itemService;

    @Override
    public void validate(final Sale sale) throws BusinessException {
        if (isNull(sale.getEvent().getId()) || !eventService.existsById(sale.getEvent().getId())) {
            throw new BusinessException(ErrorType.INVALID_SALE);
        }

        final var anyInvalidItem = !sale.getItems()
                .stream()
                .map(SaleItem::getItem)
                .map(Item::getId)
                .allMatch(itemId -> nonNull(itemId) && itemService.belongsToEvent(itemId, sale.getEvent().getId()));

        if (anyInvalidItem) {
            throw new BusinessException(ErrorType.INVALID_SALE);
        }
    }

}
