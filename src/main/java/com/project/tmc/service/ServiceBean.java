package com.project.tmc.service;

import com.project.tmc.model.document.acceptance_document.AcceptanceDocument;
import com.project.tmc.model.document.acceptance_document.AcceptanceDocumentItem;
import com.project.tmc.model.document.inventory_document.InventoryDocument;
import com.project.tmc.model.document.inventory_document.InventoryDocumentItem;
import com.project.tmc.model.document.shipment_document.ShipmentDocument;
import com.project.tmc.model.document.shipment_document.ShipmentDocumentItem;
import com.project.tmc.model.product.ProductType;
import com.project.tmc.model.user.Role;
import com.project.tmc.model.user.User;
import com.project.tmc.model.product.Vat;
import com.project.tmc.model.contractor.Bank;
import com.project.tmc.model.contractor.ContractorStatus;
import com.project.tmc.model.contractor.ContractorType;
import com.project.tmc.model.contractor.ContactPerson;
import com.project.tmc.model.contractor.ContactPersonPosition;
import com.project.tmc.model.contractor.Contractor;
import com.project.tmc.model.product.Product;
import com.project.tmc.model.product.ProductGroup;
import com.project.tmc.model.product.UnitOfMeasure;
import com.project.tmc.module.PasswordGeneratingModule;
import com.project.tmc.repository.admin.ProductTypeRepository;
import com.project.tmc.repository.admin.RoleRepository;
import com.project.tmc.repository.admin.UserRepository;
import com.project.tmc.repository.admin.VatRepository;
import com.project.tmc.repository.contrantor.BankRepository;
import com.project.tmc.repository.contrantor.ContractorStatusRepository;
import com.project.tmc.repository.contrantor.provider.ContactPersonPositionRepository;
import com.project.tmc.repository.contrantor.provider.ContactPersonRepository;
import com.project.tmc.repository.contrantor.provider.ProviderRepository;
import com.project.tmc.repository.contrantor.ContractorTypeRepository;
import com.project.tmc.repository.document.acceptance_document.AcceptanceDocumentItemRepository;
import com.project.tmc.repository.document.acceptance_document.AcceptanceDocumentRepository;
import com.project.tmc.repository.document.inventory_document.InventoryDocumentItemRepository;
import com.project.tmc.repository.document.inventory_document.InventoryDocumentRepository;
import com.project.tmc.repository.document.shipment_document.ShipmentDocumentItemRepository;
import com.project.tmc.repository.document.shipment_document.ShipmentDocumentRepository;
import com.project.tmc.repository.product.ProductGroupRepository;
import com.project.tmc.repository.product.ProductRepository;
import com.project.tmc.repository.product.UnitOfMeasureRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBean {

    // Admin Services
    //------------------------------------------------------------------------------------------------------------------

    @Bean
    public GenericCrudService<User> userService(UserRepository repository) {
        return new GenericCrudServiceImpl<>(repository) {
            private final PasswordGeneratingModule passwordGeneratingModule = new PasswordGeneratingModule();

            @Override
            public void update(User user) {
                user.setPassword(user.getPassword().isEmpty()
                        ? getById(user.getId()).getPassword()
                        : passwordGeneratingModule.encodePassword(user.getPassword()));

                super.update(user);
            }

            @Override
            public void save(User user) {
                if (user.getPassword().isEmpty()) {
                    user.setPassword(passwordGeneratingModule.getNewPassword());
                }

                super.save(user);
            }
        };
    }

    @Bean
    public GenericCrudService<Role> roleService(RoleRepository repository) {
        return new GenericCrudServiceImpl<>(repository);
    }

    // Product Services
    //------------------------------------------------------------------------------------------------------------------

    @Bean
    public GenericCrudService<UnitOfMeasure> unitOfMeasureService(UnitOfMeasureRepository repository) {
        return new GenericCrudServiceImpl<>(repository);
    }

    @Bean
    public GenericCrudService<Product> productService(ProductRepository repository) {
        return new GenericCrudServiceImpl<>(repository);
    }

    @Bean
    public GenericCrudService<ProductType> productTypeService(ProductTypeRepository repository) {
        return new GenericCrudServiceImpl<>(repository);
    }

    @Bean GenericCrudService<ProductGroup> productGroupService(ProductGroupRepository repository) {
        return new GenericCrudServiceImpl<>(repository);
    }

    @Bean
    public GenericCrudService<Vat> vatService(VatRepository repository) {
        return new GenericCrudServiceImpl<>(repository);
    }

    // Contractor Services
    //------------------------------------------------------------------------------------------------------------------

    @Bean
    public GenericCrudService<Bank> bankService(BankRepository repository) {
        return new GenericCrudServiceImpl<>(repository);
    }

    @Bean
    public GenericCrudService<ContractorStatus> contractorStatusService(ContractorStatusRepository repository) {
        return new GenericCrudServiceImpl<>(repository);
    }

    @Bean
    public GenericCrudService<ContactPersonPosition> contactPersonPositionService(ContactPersonPositionRepository repository) {
        return new GenericCrudServiceImpl<>(repository);
    }

    @Bean
    public GenericCrudService<ContactPerson> contactPersonService(ContactPersonRepository repository) {
        return new GenericCrudServiceImpl<>(repository);
    }

    @Bean
    public GenericCrudService<ContractorType> contractorTypeService(ContractorTypeRepository repository) {
        return new GenericCrudServiceImpl<>(repository);
    }

    @Bean
    public GenericCrudService<Contractor> providerService(ProviderRepository repository) {
        return new GenericCrudServiceImpl<>(repository);
    }

    // Document Services (AcceptanceDocument)
    //------------------------------------------------------------------------------------------------------------------

    @Bean
    public GenericCrudService<AcceptanceDocument> acceptanceDocumentService(AcceptanceDocumentRepository repository) {
        return new GenericCrudServiceImpl<>(repository);
    }

    @Bean
    public GenericCrudService<AcceptanceDocumentItem> acceptanceDocumentItemService(AcceptanceDocumentItemRepository repository) {
        return new GenericCrudServiceImpl<>(repository);
    }

    // Document Services (InventoryDocument)
    //------------------------------------------------------------------------------------------------------------------

    @Bean
    public GenericCrudService<InventoryDocument> inventoryDocumentService(InventoryDocumentRepository repository) {
        return new GenericCrudServiceImpl<>(repository);
    }

    @Bean
    public GenericCrudService<InventoryDocumentItem> inventoryDocumentItemService(InventoryDocumentItemRepository repository) {
        return new GenericCrudServiceImpl<>(repository);
    }

    // Document Services (ShipmentDocument)
    //------------------------------------------------------------------------------------------------------------------

    @Bean
    public GenericCrudService<ShipmentDocument> shipmentDocumentService(ShipmentDocumentRepository repository) {
        return new GenericCrudServiceImpl<>(repository);
    }

    @Bean
    public GenericCrudService<ShipmentDocumentItem> shipmentDocumentItemService(ShipmentDocumentItemRepository repository) {
        return new GenericCrudServiceImpl<>(repository);
    }
}
