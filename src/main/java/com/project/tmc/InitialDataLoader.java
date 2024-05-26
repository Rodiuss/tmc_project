package com.project.tmc;

import com.project.tmc.model.admin.ProductType;
import com.project.tmc.model.admin.Role;
import com.project.tmc.model.admin.User;
import com.project.tmc.model.admin.Vat;
import com.project.tmc.model.product.*;
import com.project.tmc.module.PasswordGeneratingModule;
import com.project.tmc.service.admin.ProductTypeService;
import com.project.tmc.service.admin.RoleService;
import com.project.tmc.service.admin.UserService;
import com.project.tmc.service.admin.VatService;
import com.project.tmc.service.product.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitialDataLoader implements CommandLineRunner {
    private final RoleService roleService;
    private final UserService userService;

    private final UnitOfMeasureService unitOfMeasureService;
    private final ProductGroupService productGroupService;
    private final ProductService productService;
    private final VatService vatService;
    private final ProductTypeService productTypeService;

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

        if (productGroupService.isEmpty() && unitOfMeasureService.isEmpty() && productTypeService.isEmpty() && vatService.isEmpty()) {
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
    }
}
