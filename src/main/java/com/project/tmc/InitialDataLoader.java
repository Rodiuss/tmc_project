package com.project.tmc;

import com.project.tmc.model.document.acceptance_document.AcceptanceDocument;
import com.project.tmc.model.document.acceptance_document.AcceptanceDocumentItem;
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
import com.project.tmc.model.product.*;
import com.project.tmc.module.PasswordGeneratingModule;
import com.project.tmc.service.GenericCrudService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitialDataLoader implements CommandLineRunner {
    private final GenericCrudService<Role> roleService;
    private final GenericCrudService<User> userService;

    private final GenericCrudService<UnitOfMeasure> unitOfMeasureService;
    private final GenericCrudService<ProductGroup> productGroupService;
    private final GenericCrudService<Product> productService;
    private final GenericCrudService<Vat> vatService;
    private final GenericCrudService<ProductType> productTypeService;

    private final GenericCrudService<Bank> bankService;
    private final GenericCrudService<ContractorStatus> contractorStatusService;
    private final GenericCrudService<ContractorType> contractorTypeService;

    private final GenericCrudService<Contractor> providerService;
    private final GenericCrudService<ContactPersonPosition> contactPersonPositionService;
    private final GenericCrudService<ContactPerson> contactPersonService;

    private final GenericCrudService<AcceptanceDocument> acceptanceDocumentService;
    private final GenericCrudService<AcceptanceDocumentItem> acceptanceDocumentItemService;

    private final PasswordGeneratingModule passwordGeneratingModule;

    @Override
    public void run(String... args) throws Exception {
        if (roleService.isEmpty()) {
            List<Role> roles = new ArrayList<>(){{
                add(Role.builder()
                        .name("Администратор")
                        .frameworkName("ADMIN")
                        .build()
                );
                add(Role.builder()
                        .name("Клиент")
                        .frameworkName("CLIENT")
                        .build()
                );
            }};

            List<User> users = new ArrayList<>(){{
                add(User.builder()
                        .email("rodion.boy@mail.ru")
                        .surname("Горбачев")
                        .name("Родион")
                        .patronymic("Дмитриевич")
                        .individualTaxpayerNumber("123456789098")
                        .password(passwordGeneratingModule.encodePassword("100"))
                        .roles(roles)
                        .build()
                );
                add(User.builder()
                        .email("test@test.test")
                        .surname("Тестов")
                        .name("Тест")
                        .patronymic("Тестович")
                        .individualTaxpayerNumber("876543211231")
                        .password(passwordGeneratingModule.encodePassword("100"))
                        .roles(roles.subList(1, 2))
                        .build()
                );
            }};

            roleService.saveAll(roles);
            userService.saveAll(users);
        }

        if (productGroupService.isEmpty()
                && unitOfMeasureService.isEmpty()
                && productTypeService.isEmpty()
                && vatService.isEmpty()
                && productService.isEmpty()) {
            List<ProductGroup> productGroups = new ArrayList<>() {{
                ProductGroup fProductGroup = ProductGroup.builder()
                        .name("Первая группа товаров")
                        .build();
                ProductGroup sProductGroup = ProductGroup.builder()
                        .name("Вторая группа товаров")
                        .parentGroup(fProductGroup)
                        .build();

                add(fProductGroup);
                add(sProductGroup);
                add(ProductGroup.builder()
                        .name("Третья группа товаров")
                        .parentGroup(sProductGroup)
                        .build());
            }};

            List<UnitOfMeasure> unitsOfMeasure = new ArrayList<>(){{
               add(UnitOfMeasure.builder()
                       .name("Штука")
                       .build());
               add(UnitOfMeasure.builder()
                        .name("Килограмм")
                        .build());
            }};

            List<ProductType> productTypes = new ArrayList<>(){{
                add(ProductType.builder()
                        .programName("NORMAL")
                        .name("Вся продукция")
                        .build()
                );
                add(ProductType.builder()
                        .programName("SERVICE")
                        .name("Услуги")
                        .build()
                );
                add(ProductType.builder()
                        .programName("ALCOHOL_MARKED")
                        .name("Маркированный алкоголь")
                        .build()
                );
                add(ProductType.builder()
                        .programName("ALCOHOL_NOT_MARKED")
                        .name("Немаркированный алкоголь")
                        .build()
                );
                add(ProductType.builder()
                        .programName("TOBACCO_MARKED")
                        .name("Маркированный табак")
                        .build()
                );
                add(ProductType.builder()
                        .programName("TOBACCO_PRODUCTS_MARKED")
                        .name("Маркированный альтернативный табак")
                        .build()
                );
                add(ProductType.builder()
                        .programName("SHOES_MARKED")
                        .name("Маркированная обувь")
                        .build()
                );
                add(ProductType.builder()
                        .programName("MEDICINE_MARKED")
                        .name("Лекарства и медицинская продукция, подлежащие маркировке")
                        .build()
                );
                add(ProductType.builder()
                        .programName("PERFUME_MARKED")
                        .name("Маркированная парфюмерия")
                        .build()
                );
                add(ProductType.builder()
                        .programName("PHOTOS_MARKED")
                        .name("Маркированные фототовары")
                        .build()
                );
                add(ProductType.builder()
                        .programName("TYRES_MARKED")
                        .name("Маркированные шины")
                        .build()
                );
                add(ProductType.builder()
                        .programName("LIGHT_INDUSTRY_MARKED")
                        .name("Маркированная продукция легкой промышленности")
                        .build()
                );
                add(ProductType.builder()
                        .programName("DAIRY_MARKED")
                        .name("Маркированная молочная продукция")
                        .build()
                );
                add(ProductType.builder()
                        .programName("WATER_MARKED")
                        .name("Маркированная вода")
                        .build()
                );
                add(ProductType.builder()
                        .programName("BIKE_MARKED")
                        .name("Маркированные велотовары")
                        .build()
                );
                add(ProductType.builder()
                        .programName("FUR_MARKED")
                        .name("Маркированные меховые изделия")
                        .build()
                );
                add(ProductType.builder()
                        .programName("JEWELRY_MARKED")
                        .name("Маркированное ювелирное изделие")
                        .build()
                );
            }};

            List<Vat> vats = new ArrayList<>(){{
                add(Vat.builder()
                        .programName("NO_VAT")
                        .name("Не облагается НДС")
                        .build()
                );
                add(Vat.builder()
                        .programName("VAT_10")
                        .name("НДС 10%")
                        .build()
                );
                add(Vat.builder()
                        .programName("VAT_18")
                        .name("НДС 20%")
                        .build()
                );
                add(Vat.builder()
                        .programName("VAT_10_110")
                        .name("НДС расчетный 10/110")
                        .build()
                );
                add(Vat.builder()
                        .programName("VAT_18_118")
                        .name("НДС расчетный 20/120")
                        .build()
                );
                add(Vat.builder()
                        .programName("VAT_0")
                        .name("НДС по ставке 0%")
                        .build()
                );
            }};

            List<Product> products = new ArrayList<>(){{
                add(Product.builder()
                        .name("Первая продукция")
                        .article("12345")
                        .barcode("54321")
                        .code("1234554321")
                        .purchasePrice(200.0)
                        .quantity(30)
                        .unit(unitsOfMeasure.get(0))
                        .group(productGroups.get(1))
                        .sellingPrice(300.0)
                        .vat(vats.get(2))
                        .type(productTypes.get(3))
                        .inStock(true)
                        .description("Короткое описание первой продукции")
                        .excisable(false)
                        .type(productTypes.get(0))
                        .build()
                );
                add(Product.builder()
                        .name("Вторая продукция")
                        .article("54321")
                        .barcode("12345")
                        .code("5432112345")
                        .purchasePrice(300.0)
                        .quantity(20)
                        .unit(unitsOfMeasure.get(1))
                        .group(productGroups.get(0))
                        .sellingPrice(400.0)
                        .vat(vats.get(3))
                        .type(productTypes.get(0))
                        .inStock(true)
                        .description("Короткое описание второй продукции")
                        .excisable(false)
                        .type(productTypes.get(2))
                        .build()
                );
            }};

            productGroupService.saveAll(productGroups);
            unitOfMeasureService.saveAll(unitsOfMeasure);
            vatService.saveAll(vats);
            productTypeService.saveAll(productTypes);
            productService.saveAll(products);
        }

        if (providerService.isEmpty()
                && contactPersonService.isEmpty()
                && contactPersonPositionService.isEmpty()

                && bankService.isEmpty()
                && contractorStatusService.isEmpty()
                && contractorTypeService.isEmpty()) {
            List<Bank> banks = new ArrayList<>() {{
                add(Bank.builder()
                        .name("ВОЛОГОДСКОЕ ОТДЕЛЕНИЕ N8638 ПАО СБЕРБАНК")
                        .bic("041909644")
                        .correspondentAccount("30101810900000000644")
                        .build());
                add(Bank.builder()
                        .name("ФИЛИАЛ ВОЛОГОДСКИЙ БАНКА ВТБ (ПАО)")
                        .bic("041909722")
                        .correspondentAccount("30101810000000000722")
                        .build());
                add(Bank.builder()
                        .name("АО \"Тинькофф Банк\"")
                        .bic("044525974")
                        .correspondentAccount("30101810145250000974")
                        .build());
                add(Bank.builder()
                        .name("АО \"СЕВЕРСТРОЙБАНК\"")
                        .bic("041909707")
                        .correspondentAccount("30101810300000000707")
                        .build());
            }};

            List<ContractorStatus> contractorStatuses = new ArrayList<>() {{
                add(ContractorStatus.builder()
                        .name("Активен")
                        .build());
                add(ContractorStatus.builder()
                        .name("Неактивен")
                        .build());
                add(ContractorStatus.builder()
                        .name("Заблокирован")
                        .build());
            }};

            List<ContractorType> contractorTypes = new ArrayList<>() {{
                add(ContractorType.builder()
                        .name("ООО")
                        .build());
                add(ContractorType.builder()
                        .name("ИП")
                        .build());
                add(ContractorType.builder()
                        .name("ПАО")
                        .build());
                add(ContractorType.builder()
                        .name("АО")
                        .build());
                add(ContractorType.builder()
                        .name("НКО")
                        .build());
                add(ContractorType.builder()
                        .name("ОП")
                        .build());
            }};

            List<Contractor> contractors = new ArrayList<>() {{
                add(Contractor.builder()
                        .name("ТехноСтройКомплект")
                        .legalAddress("123456, г. Москва, ул. Ленина, д. 100, офис 500")
                        .actualAddress("123456, г. Москва, ул. Ленина, д. 100, офис 500")
                        .tin("771234567890")
                        .kpp("771201001")
                        .ogrn("1234567890123")
                        .phoneNumber("+7(495)123-45-67")
                        .accountNumber("40702810123456789012")
                        .bank(banks.get(0))
                        .providerType(contractorTypes.get(0))
                        .contractorStatus(contractorStatuses.get(0))
                        .build());
                add(Contractor.builder()
                        .name("Зеленая Долина")
                        .legalAddress("111111, г. Санкт-Петербург, пр. Невский, д. 10, офис 200")
                        .actualAddress("111111, г. Санкт-Петербург, пр. Невский, д. 10")
                        .tin("781012345678")
                        .kpp("781001001")
                        .ogrn("1111111111111")
                        .email("info@zelenaya-dolina.ru")
                        .website("www.zelenaya-dolina.ru")
                        .accountNumber("45678901240702810123")
                        .bank(banks.get(1))
                        .providerType(contractorTypes.get(1))
                        .contractorStatus(contractorStatuses.get(0))
                        .build());
                add(Contractor.builder()
                        .name("Книжный Мир")
                        .legalAddress("345678, г. Екатеринбург, ул. Малышева, д. 50, офис 300")
                        .actualAddress("345678, г. Екатеринбург, ул. Малышева, д. 50")
                        .tin("667788990012")
                        .kpp("667701001")
                        .ogrn("2936634137176")
                        .phoneNumber("+7(495)423-43-67")
                        .accountNumber("70402813210456789237")
                        .bank(banks.get(2))
                        .providerType(contractorTypes.get(2))
                        .contractorStatus(contractorStatuses.get(0))
                        .build());
            }};

            List<ContactPersonPosition> contactPersonPositions = new ArrayList<>() {{
                add(ContactPersonPosition.builder()
                        .name("Менеджер по продажам")
                        .build());
                add(ContactPersonPosition.builder()
                        .name("Менеджер по логистике")
                        .build());
                add(ContactPersonPosition.builder()
                        .name("Специалист по контролю качества")
                        .build());
                add(ContactPersonPosition.builder()
                        .name("Технический специалист")
                        .build());
                add(ContactPersonPosition.builder()
                        .name("Юрист")
                        .build());
                add(ContactPersonPosition.builder()
                        .name("Специалист по маркетингу")
                        .build());
                add(ContactPersonPosition.builder()
                        .name("Торговый представитель")
                        .build());
            }};

            List<ContactPerson> contactPersonList = new ArrayList<>() {{
                add(ContactPerson.builder()
                        .surname("Виноградова")
                        .name("Елена")
                        .patronymic("Сидорова")
                        .phoneNumber("+7(999)987-65-43")
                        .email("elena.sidorova@company.com")
                        .contactPersonPosition(contactPersonPositions.get(0))
                        .contractor(contractors.get(0))
                        .build());
                add(ContactPerson.builder()
                        .surname("Горабчев")
                        .name("Иван")
                        .patronymic("Петров")
                        .phoneNumber("+7(999)123-45-67")
                        .email("ivan.petrov@company.com")
                        .contactPersonPosition(contactPersonPositions.get(1))
                        .contractor(contractors.get(0))
                        .build());
                add(ContactPerson.builder()
                        .name("Алексей")
                        .patronymic("Козлов")
                        .phoneNumber("+7 (999) 555-44-33")
                        .contactPersonPosition(contactPersonPositions.get(4))
                        .contractor(contractors.get(1))
                        .build());
                add(ContactPerson.builder()
                        .name("Мария")
                        .patronymic("Иванова")
                        .phoneNumber("+7(999)111-22-33")
                        .contactPersonPosition(contactPersonPositions.get(3))
                        .contractor(contractors.get(1))
                        .build());
                add(ContactPerson.builder()
                        .name("Дмитрий")
                        .patronymic("Сергеев")
                        .phoneNumber("+7 (999) 444-55-66")
                        .contactPersonPosition(contactPersonPositions.get(5))
                        .contractor(contractors.get(2))
                        .build());
            }};

            List<AcceptanceDocument> acceptanceDocuments = new ArrayList<>() {{
                add(AcceptanceDocument.builder()
                        .documentDate(Date.valueOf("2023-01-01"))
                        .documentNumber("ПР-001/2023")
                        .contractor(contractors.get(0))
                        .build());
                add(AcceptanceDocument.builder()
                        .documentDate(Date.valueOf("2023-02-02"))
                        .documentNumber("ПР-025/2023")
                        .contractor(contractors.get(1))
                        .build());
            }};

            List<AcceptanceDocumentItem> acceptanceDocumentItems = new ArrayList<>() {{
                add(AcceptanceDocumentItem.builder()
                        .acceptanceDocument(acceptanceDocuments.get(0))
                        .product(productService.getById(Long.valueOf("1")))
                        .quantity(20)
                        .build());
                add(AcceptanceDocumentItem.builder()
                        .acceptanceDocument(acceptanceDocuments.get(0))
                        .product(productService.getById(Long.valueOf("2")))
                        .quantity(10)
                        .build());
                add(AcceptanceDocumentItem.builder()
                        .acceptanceDocument(acceptanceDocuments.get(1))
                        .product(productService.getById(Long.valueOf("2")))
                        .quantity(30)
                        .build());
            }};

            bankService.saveAll(banks);
            contractorStatusService.saveAll(contractorStatuses);
            contractorTypeService.saveAll(contractorTypes);

            providerService.saveAll(contractors);
            contactPersonPositionService.saveAll(contactPersonPositions);
            contactPersonService.saveAll(contactPersonList);

            acceptanceDocumentService.saveAll(acceptanceDocuments);
            acceptanceDocumentItemService.saveAll(acceptanceDocumentItems);
        }
    }
}
