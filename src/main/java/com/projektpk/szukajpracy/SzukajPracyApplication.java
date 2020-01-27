package com.projektpk.szukajpracy;

import com.projektpk.szukajpracy.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SzukajPracyApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SzukajPracyApplication.class, args);
    }

    @Autowired
    private SearchRepository searchRepository;
    @Autowired
    private AdvertisementRepository advertisementRepository;
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private CertificateRepository certificateRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CVRepository cvRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public void run(String...args) throws Exception {

        /*
        searchRepository.save( new Search("Finanse","Księgowość","Starzysta"));
        searchRepository.save( new Search("Finanse","Bankowość","Starzysta"));
        searchRepository.save( new Search("Finanse","Rachunkowść","Starzysta"));
        searchRepository.save( new Search("Budownictwo","Praca Fizyczna","Starzysta"));
        searchRepository.save( new Search("Budownictwo","Architektura","Starzysta"));
        searchRepository.save( new Search("Budownictwo","Energetyka","Starzysta"));
        searchRepository.save( new Search("Budownictwo","Przemysłowość","Starzysta"));
        searchRepository.save( new Search("IT","Programowanie","Java"));
        searchRepository.save( new Search("IT","Programowanie","C++"));
        searchRepository.save( new Search("IT","Programowanie","JS"));
        searchRepository.save( new Search("IT","Programowanie","C#"));
        searchRepository.save( new Search("IT","Programowanie","C"));
        searchRepository.save( new Search("IT","Programowanie","GO"));
        searchRepository.save( new Search("IT","bezpieczeństwo","Starzysta"));
        searchRepository.save(new Search("IT","Testowanie","Junio"));
        searchRepository.save(new Search("IT","Bazy Danych","Senior"));
        searchRepository.save( new Search("Prawo","Windykacja","Starzysta"));
        searchRepository.save( new Search("Prawo","Prawnik","Starzysta"));
        searchRepository.save( new Search("Finanse","Księgowość","Specjalista"));
        searchRepository.save( new Search("Finanse","Bankowość","Specjalista"));
        searchRepository.save( new Search("Finanse","Rachunkowść","Specjalista"));
        searchRepository.save( new Search("Budownictwo","Praca Fizyczna","Specjalista"));
        searchRepository.save( new Search("Budownictwo","Architektura","Specjalista"));
        searchRepository.save( new Search("Budownictwo","Energetyka","Specjalista"));
        searchRepository.save( new Search("Budownictwo","Przemysłowość","Specjalista"));
        searchRepository.save( new Search("Prawo","Windykacja","Specjalista"));
        searchRepository.save( new Search("Prawo","Prawnik","Specjalista"));


        User u1 =new User("admin","haslo","admin@wp.pl");
        User u2 =new User("Login2","haslo","Login2@wp.pl");
        User u3 =new User("Login3","haslo","Login3@wp.pl");
        User u4 =new User("Login4","haslo","Login4@wp.pl");
        User u5 =new User("Login5","haslo","Login5@wp.pl");
        User u6 =new User("Login6","haslo","Login6@wp.pl");
        User u7 =new User("Login7","haslo","Login7@wp.pl");
        User u8 =new User("Login8","haslo","Login8@wp.pl");
        User u9 =new User("Login9","haslo","Login9@wp.pl");
        User u10 =new User("Login10","haslo","Login10@wp.pl");
        User u11 =new User("Login11","haslo","Login11@wp.pl");
        User u12 =new User("Login12","haslo","Login12@wp.pl");

        userRepository.save(u1);
        userRepository.save(u2);
        userRepository.save(u3);
        userRepository.save(u4);
        userRepository.save(u5);
        userRepository.save(u6);
        userRepository.save(u7);
        userRepository.save(u8);
        userRepository.save(u9);
        userRepository.save(u10);
        userRepository.save(u11);
        userRepository.save(u12);

        Customer c1 = new Customer("Anna","Kawalska","Kraków",787656465,54986653);
        Customer c2 =new Customer("Paulina","Nowak","Kraków",985432765,345);
        Customer c3 =new Customer("Zosia","Rybka","Kraków",876527654,9999999);
        Customer c4 =new Customer("Kuba","Pokolko","Kraków",985432876,987654678);
        Customer c5 =new Customer("Kamil","Bykoro","Kraków",987654397,345679765);
        Customer c6 = new Customer("Janusz","Nowak","jnKrakówhb",987498765,875434567);

        c1.setUser_Customer(u1);
        c2.setUser_Customer(u2);
        c3.setUser_Customer(u3);
        c4.setUser_Customer(u4);
        c5.setUser_Customer(u5);
        c6.setUser_Customer(u6);

        customerRepository.save(c1);
        customerRepository.save(c2);
        customerRepository.save(c3);
        customerRepository.save(c4);
        customerRepository.save(c5);
        customerRepository.save(c6);

        CV cv1 = new CV("Anna Kawalska","Informacje o edukacji","Informacje o hobby","Informacje o umiejętnościach","Informacje o doświadczeniu");
        CV cv2 =new CV("Paulina Nowak","Informacje o edukacji","Informacje o hobby","Informacje o umiejętnościach","Informacje o doświadczeniu");
        CV cv3 =new CV("Zosia Rybka","Informacje o edukacji","Informacje o hobby","Informacje o umiejętnościach","Informacje o doświadczeniu");
        CV cv4 =new CV("Kuba Pokolko","Informacje o edukacji","Informacje o hobby","Informacje o umiejętnościach","Informacje o doświadczeniu");
        CV cv5 =new CV("Kamil Bykoro","Informacje o edukacji","Informacje o hobby","Informacje o umiejętnościach","Informacje o doświadczeniu");
        CV cv6 = new CV("Janusz Nowak","Informacje o edukacji","Informacje o hobby","Informacje o umiejętnościach","Informacje o doświadczeniu");

        cv1.setCv(c1);
        cv2.setCv(c2);
        cv3.setCv(c3);
        cv4.setCv(c4);
        cv5.setCv(c5);
        cv6.setCv(c6);

        cvRepository.save(cv1);
        cvRepository.save(cv2);
        cvRepository.save(cv3);
        cvRepository.save(cv4);
        cvRepository.save(cv5);
        cvRepository.save(cv6);

        Company co1 = new Company("Paroza","ulica1","Kraków","Firma1@wp.pl",43678,9987,776);
        Company co2 = new Company("Firma2","ulica2","Kraków","Firma2@wp.pl",43565,9864,9876);
        Company co3 = new Company("Firma3","ulica3","Kraków","Firma3@wp.pl",34567,9896,98765);
        Company co4 = new Company("Firma4","ulica4","Kraków","Firma4@wp.pl",98765,98766,8765);
        Company co5 = new Company("Firma5","ulica5","Kraków","Firma5@wp.pl",87654,875655,87654);
        Company co6 = new Company("Firma6","ulica6","Kraków","Firma6@wp.pl",98764,98765,876555);

        co1.setUser_company(u7);
        co2.setUser_company(u8);
        co3.setUser_company(u9);
        co4.setUser_company(u10);
        co5.setUser_company(u11);
        co6.setUser_company(u12);

        companyRepository.save(co1);
        companyRepository.save(co2);
        companyRepository.save(co3);
        companyRepository.save(co4);
        companyRepository.save(co5);
        companyRepository.save(co6);

        Advertisement ad1 = new Advertisement("Ogłoszenie 1","IT","Kraków",4000,"Paroza","2019-09-02","Umawa zlecenie","Treść Treść Treść");
        Advertisement ad2 = new Advertisement("Ogłoszenie 2","IT","Kraków",700,"Firma","2019-09-02","Umawa zlecenie","Treść Treść Treść");
        Advertisement ad3 = new Advertisement("Ogłoszenie 3","IT","Kraków",13000,"Firma","2019-09-02","Umawa zlecenie","Treść Treść Treść");
        Advertisement ad4 = new Advertisement("Ogłoszenie 4","IT","Kraków",8765,"Firma","2019-09-02","B2B","Treść Treść Treść");
        Advertisement ad5 = new Advertisement("Ogłoszenie 5","IT","Kraków",3000,"Firma","2019-09-02","B2B","Treść Treść Treść");
        Advertisement ad6 = new Advertisement("Ogłoszenie 6","IT","Kraków",7000,"Firma","2019-09-02","Umowa o dzieło","Treść Treść Treść");

        ad1.setCompany_Advertisement(co1);
        ad2.setCompany_Advertisement(co2);
        ad3.setCompany_Advertisement(co3);
        ad4.setCompany_Advertisement(co4);
        ad5.setCompany_Advertisement(co5);
        ad6.setCompany_Advertisement(co6);

        advertisementRepository.save(ad1);
        advertisementRepository.save(ad2);
        advertisementRepository.save(ad3);
        advertisementRepository.save(ad4);
        advertisementRepository.save(ad5);
        advertisementRepository.save(ad6);


        Application a1 = new Application("Paroza","Pilnie poszukiwany stażysta! IT","mail@wp.pl",876542187,"adres");
        Application a2 = new Application("Aplikacja 2","nazwisko","mail@wp.pl",987643187,"adres");
        Application a3 = new Application("Aplikacja 3","nazwisko","mail@wp.pl",876532187,"adres");
        Application a4 = new Application("Aplikacja 4","nazwisko","mail@wp.pl",876542187,"adres");
        Application a5 = new Application("Aplikacja 5","nazwisko","mail@wp.pl",876543217,"adres");
        Application a6 = new Application("Aplikacja 6","nazwisko","mail@wp.pl",765432187,"adres");

        a2.setCompany_Application(co1);
        a2.setCustomers_Application(c1);
        a2.setAdvertisement(ad1);

        a3.setCompany_Application(co2);
        a3.setCustomers_Application(c2);
        a3.setAdvertisement(ad2);

        a4.setCompany_Application(co3);
        a4.setCustomers_Application(c3);
        a4.setAdvertisement(ad3);

        a5.setCompany_Application(co4);
        a5.setCustomers_Application(c4);
        a5.setAdvertisement(ad4);

        a6.setCompany_Application(co5);
        a6.setCustomers_Application(c5);
        a6.setAdvertisement(ad5);

        a1.setCompany_Application(co6);
        a1.setCustomers_Application(c6);
        a1.setAdvertisement(ad6);

        applicationRepository.save(a1);
        applicationRepository.save(a2);
        applicationRepository.save(a3);
        applicationRepository.save(a4);
        applicationRepository.save(a5);
        applicationRepository.save(a6);

        Course ce1 = new Course("Kurs","Specjalistyczny");
        Course ce2 = new Course("Kurs","Specjalistyczny");
        Course ce3 = new Course("Kurs","Specjalistyczny");
        Course ce4 = new Course("Kurs","Specjalistyczny");
        Course ce5 = new Course("Kurs","Specjalistyczny");
        Course ce6 = new Course("Kurs","Specjalistyczny");

        ce1.setCustomer_Curse(c1);
        ce2.setCustomer_Curse(c2);
        ce3.setCustomer_Curse(c3);
        ce4.setCustomer_Curse(c4);
        ce5.setCustomer_Curse(c5);
        ce6.setCustomer_Curse(c6);

        courseRepository.save(ce1);
        courseRepository.save(ce2);
        courseRepository.save(ce3);
        courseRepository.save(ce4);
        courseRepository.save(ce5);
        courseRepository.save(ce6);

        Certificate cf1 = new Certificate("Certyfikat","Specjalistyczny");
        Certificate cf2 = new Certificate("Certyfikat","Specjalistyczny");
        Certificate cf3 = new Certificate("Certyfikat","Specjalistyczny");
        Certificate cf4 = new Certificate("Certyfikat","Specjalistyczny");
        Certificate cf5 = new Certificate("Certyfikat","Specjalistyczny");
        Certificate cf6 = new Certificate("Certyfikat","Specjalistyczny");

        cf1.setCustomer_Certficate(c1);
        cf2.setCustomer_Certficate(c2);
        cf3.setCustomer_Certficate(c3);
        cf4.setCustomer_Certficate(c4);
        cf5.setCustomer_Certficate(c5);
        cf6.setCustomer_Certficate(c6);
*/
    }
}
