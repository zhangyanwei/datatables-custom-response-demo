package demo.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataService {

    @Autowired
    private ObjectMapper objectMapper;

    private static final String DATA = "[\n" +
            "    {\n" +
            "      \"name\": \"Tiger Nixon\",\n" +
            "      \"position\": \"System Architect\",\n" +
            "      \"office\": \"Edinburgh\",\n" +
            "      \"number\": \"5421\",\n" +
            "      \"startDate\": \"2011/04/25\",\n" +
            "      \"salary\": \"$320,800\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Garrett Winters\",\n" +
            "      \"position\": \"Accountant\",\n" +
            "      \"office\": \"Tokyo\",\n" +
            "      \"number\": \"8422\",\n" +
            "      \"startDate\": \"2011/07/25\",\n" +
            "      \"salary\": \"$170,750\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Ashton Cox\",\n" +
            "      \"position\": \"Junior Technical Author\",\n" +
            "      \"office\": \"San Francisco\",\n" +
            "      \"number\": \"1562\",\n" +
            "      \"startDate\": \"2009/01/12\",\n" +
            "      \"salary\": \"$86,000\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Cedric Kelly\",\n" +
            "      \"position\": \"Senior Javascript Developer\",\n" +
            "      \"office\": \"Edinburgh\",\n" +
            "      \"number\": \"6224\",\n" +
            "      \"startDate\": \"2012/03/29\",\n" +
            "      \"salary\": \"$433,060\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Airi Satou\",\n" +
            "      \"position\": \"Accountant\",\n" +
            "      \"office\": \"Tokyo\",\n" +
            "      \"number\": \"5407\",\n" +
            "      \"startDate\": \"2008/11/28\",\n" +
            "      \"salary\": \"$162,700\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Brielle Williamson\",\n" +
            "      \"position\": \"Integration Specialist\",\n" +
            "      \"office\": \"New York\",\n" +
            "      \"number\": \"4804\",\n" +
            "      \"startDate\": \"2012/12/02\",\n" +
            "      \"salary\": \"$372,000\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Herrod Chandler\",\n" +
            "      \"position\": \"Sales Assistant\",\n" +
            "      \"office\": \"San Francisco\",\n" +
            "      \"number\": \"9608\",\n" +
            "      \"startDate\": \"2012/08/06\",\n" +
            "      \"salary\": \"$137,500\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Rhona Davidson\",\n" +
            "      \"position\": \"Integration Specialist\",\n" +
            "      \"office\": \"Tokyo\",\n" +
            "      \"number\": \"6200\",\n" +
            "      \"startDate\": \"2010/10/14\",\n" +
            "      \"salary\": \"$327,900\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Colleen Hurst\",\n" +
            "      \"position\": \"Javascript Developer\",\n" +
            "      \"office\": \"San Francisco\",\n" +
            "      \"number\": \"2360\",\n" +
            "      \"startDate\": \"2009/09/15\",\n" +
            "      \"salary\": \"$205,500\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Sonya Frost\",\n" +
            "      \"position\": \"Software Engineer\",\n" +
            "      \"office\": \"Edinburgh\",\n" +
            "      \"number\": \"1667\",\n" +
            "      \"startDate\": \"2008/12/13\",\n" +
            "      \"salary\": \"$103,600\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Jena Gaines\",\n" +
            "      \"position\": \"Office Manager\",\n" +
            "      \"office\": \"London\",\n" +
            "      \"number\": \"3814\",\n" +
            "      \"startDate\": \"2008/12/19\",\n" +
            "      \"salary\": \"$90,560\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Quinn Flynn\",\n" +
            "      \"position\": \"Support Lead\",\n" +
            "      \"office\": \"Edinburgh\",\n" +
            "      \"number\": \"9497\",\n" +
            "      \"startDate\": \"2013/03/03\",\n" +
            "      \"salary\": \"$342,000\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Charde Marshall\",\n" +
            "      \"position\": \"Regional Director\",\n" +
            "      \"office\": \"San Francisco\",\n" +
            "      \"number\": \"6741\",\n" +
            "      \"startDate\": \"2008/10/16\",\n" +
            "      \"salary\": \"$470,600\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Haley Kennedy\",\n" +
            "      \"position\": \"Senior Marketing Designer\",\n" +
            "      \"office\": \"London\",\n" +
            "      \"number\": \"3597\",\n" +
            "      \"startDate\": \"2012/12/18\",\n" +
            "      \"salary\": \"$313,500\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Tatyana Fitzpatrick\",\n" +
            "      \"position\": \"Regional Director\",\n" +
            "      \"office\": \"London\",\n" +
            "      \"number\": \"1965\",\n" +
            "      \"startDate\": \"2010/03/17\",\n" +
            "      \"salary\": \"$385,750\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Michael Silva\",\n" +
            "      \"position\": \"Marketing Designer\",\n" +
            "      \"office\": \"London\",\n" +
            "      \"number\": \"1581\",\n" +
            "      \"startDate\": \"2012/11/27\",\n" +
            "      \"salary\": \"$198,500\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Paul Byrd\",\n" +
            "      \"position\": \"Chief Financial Officer (CFO)\",\n" +
            "      \"office\": \"New York\",\n" +
            "      \"number\": \"3059\",\n" +
            "      \"startDate\": \"2010/06/09\",\n" +
            "      \"salary\": \"$725,000\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Gloria Little\",\n" +
            "      \"position\": \"Systems Administrator\",\n" +
            "      \"office\": \"New York\",\n" +
            "      \"number\": \"1721\",\n" +
            "      \"startDate\": \"2009/04/10\",\n" +
            "      \"salary\": \"$237,500\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Bradley Greer\",\n" +
            "      \"position\": \"Software Engineer\",\n" +
            "      \"office\": \"London\",\n" +
            "      \"number\": \"2558\",\n" +
            "      \"startDate\": \"2012/10/13\",\n" +
            "      \"salary\": \"$132,000\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Dai Rios\",\n" +
            "      \"position\": \"Personnel Lead\",\n" +
            "      \"office\": \"Edinburgh\",\n" +
            "      \"number\": \"2290\",\n" +
            "      \"startDate\": \"2012/09/26\",\n" +
            "      \"salary\": \"$217,500\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Jenette Caldwell\",\n" +
            "      \"position\": \"Development Lead\",\n" +
            "      \"office\": \"New York\",\n" +
            "      \"number\": \"1937\",\n" +
            "      \"startDate\": \"2011/09/03\",\n" +
            "      \"salary\": \"$345,000\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Yuri Berry\",\n" +
            "      \"position\": \"Chief Marketing Officer (CMO)\",\n" +
            "      \"office\": \"New York\",\n" +
            "      \"number\": \"6154\",\n" +
            "      \"startDate\": \"2009/06/25\",\n" +
            "      \"salary\": \"$675,000\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Caesar Vance\",\n" +
            "      \"position\": \"Pre-Sales Support\",\n" +
            "      \"office\": \"New York\",\n" +
            "      \"number\": \"8330\",\n" +
            "      \"startDate\": \"2011/12/12\",\n" +
            "      \"salary\": \"$106,450\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Doris Wilder\",\n" +
            "      \"position\": \"Sales Assistant\",\n" +
            "      \"office\": \"Sidney\",\n" +
            "      \"number\": \"3023\",\n" +
            "      \"startDate\": \"2010/09/20\",\n" +
            "      \"salary\": \"$85,600\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Angelica Ramos\",\n" +
            "      \"position\": \"Chief Executive Officer (CEO)\",\n" +
            "      \"office\": \"London\",\n" +
            "      \"number\": \"5797\",\n" +
            "      \"startDate\": \"2009/10/09\",\n" +
            "      \"salary\": \"$1,200,000\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Gavin Joyce\",\n" +
            "      \"position\": \"Developer\",\n" +
            "      \"office\": \"Edinburgh\",\n" +
            "      \"number\": \"8822\",\n" +
            "      \"startDate\": \"2010/12/22\",\n" +
            "      \"salary\": \"$92,575\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Jennifer Chang\",\n" +
            "      \"position\": \"Regional Director\",\n" +
            "      \"office\": \"Singapore\",\n" +
            "      \"number\": \"9239\",\n" +
            "      \"startDate\": \"2010/11/14\",\n" +
            "      \"salary\": \"$357,650\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Brenden Wagner\",\n" +
            "      \"position\": \"Software Engineer\",\n" +
            "      \"office\": \"San Francisco\",\n" +
            "      \"number\": \"1314\",\n" +
            "      \"startDate\": \"2011/06/07\",\n" +
            "      \"salary\": \"$206,850\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Fiona Green\",\n" +
            "      \"position\": \"Chief Operating Officer (COO)\",\n" +
            "      \"office\": \"San Francisco\",\n" +
            "      \"number\": \"2947\",\n" +
            "      \"startDate\": \"2010/03/11\",\n" +
            "      \"salary\": \"$850,000\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Shou Itou\",\n" +
            "      \"position\": \"Regional Marketing\",\n" +
            "      \"office\": \"Tokyo\",\n" +
            "      \"number\": \"8899\",\n" +
            "      \"startDate\": \"2011/08/14\",\n" +
            "      \"salary\": \"$163,000\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Michelle House\",\n" +
            "      \"position\": \"Integration Specialist\",\n" +
            "      \"office\": \"Sidney\",\n" +
            "      \"number\": \"2769\",\n" +
            "      \"startDate\": \"2011/06/02\",\n" +
            "      \"salary\": \"$95,400\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Suki Burks\",\n" +
            "      \"position\": \"Developer\",\n" +
            "      \"office\": \"London\",\n" +
            "      \"number\": \"6832\",\n" +
            "      \"startDate\": \"2009/10/22\",\n" +
            "      \"salary\": \"$114,500\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Prescott Bartlett\",\n" +
            "      \"position\": \"Technical Author\",\n" +
            "      \"office\": \"London\",\n" +
            "      \"number\": \"3606\",\n" +
            "      \"startDate\": \"2011/05/07\",\n" +
            "      \"salary\": \"$145,000\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Gavin Cortez\",\n" +
            "      \"position\": \"Team Leader\",\n" +
            "      \"office\": \"San Francisco\",\n" +
            "      \"number\": \"2860\",\n" +
            "      \"startDate\": \"2008/10/26\",\n" +
            "      \"salary\": \"$235,500\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Martena Mccray\",\n" +
            "      \"position\": \"Post-Sales support\",\n" +
            "      \"office\": \"Edinburgh\",\n" +
            "      \"number\": \"8240\",\n" +
            "      \"startDate\": \"2011/03/09\",\n" +
            "      \"salary\": \"$324,050\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Unity Butler\",\n" +
            "      \"position\": \"Marketing Designer\",\n" +
            "      \"office\": \"San Francisco\",\n" +
            "      \"number\": \"5384\",\n" +
            "      \"startDate\": \"2009/12/09\",\n" +
            "      \"salary\": \"$85,675\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Howard Hatfield\",\n" +
            "      \"position\": \"Office Manager\",\n" +
            "      \"office\": \"San Francisco\",\n" +
            "      \"number\": \"7031\",\n" +
            "      \"startDate\": \"2008/12/16\",\n" +
            "      \"salary\": \"$164,500\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Hope Fuentes\",\n" +
            "      \"position\": \"Secretary\",\n" +
            "      \"office\": \"San Francisco\",\n" +
            "      \"number\": \"6318\",\n" +
            "      \"startDate\": \"2010/02/12\",\n" +
            "      \"salary\": \"$109,850\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Vivian Harrell\",\n" +
            "      \"position\": \"Financial Controller\",\n" +
            "      \"office\": \"San Francisco\",\n" +
            "      \"number\": \"9422\",\n" +
            "      \"startDate\": \"2009/02/14\",\n" +
            "      \"salary\": \"$452,500\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Timothy Mooney\",\n" +
            "      \"position\": \"Office Manager\",\n" +
            "      \"office\": \"London\",\n" +
            "      \"number\": \"7580\",\n" +
            "      \"startDate\": \"2008/12/11\",\n" +
            "      \"salary\": \"$136,200\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Jackson Bradshaw\",\n" +
            "      \"position\": \"Director\",\n" +
            "      \"office\": \"New York\",\n" +
            "      \"number\": \"1042\",\n" +
            "      \"startDate\": \"2008/09/26\",\n" +
            "      \"salary\": \"$645,750\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Olivia Liang\",\n" +
            "      \"position\": \"Support Engineer\",\n" +
            "      \"office\": \"Singapore\",\n" +
            "      \"number\": \"2120\",\n" +
            "      \"startDate\": \"2011/02/03\",\n" +
            "      \"salary\": \"$234,500\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Bruno Nash\",\n" +
            "      \"position\": \"Software Engineer\",\n" +
            "      \"office\": \"London\",\n" +
            "      \"number\": \"6222\",\n" +
            "      \"startDate\": \"2011/05/03\",\n" +
            "      \"salary\": \"$163,500\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Sakura Yamamoto\",\n" +
            "      \"position\": \"Support Engineer\",\n" +
            "      \"office\": \"Tokyo\",\n" +
            "      \"number\": \"9383\",\n" +
            "      \"startDate\": \"2009/08/19\",\n" +
            "      \"salary\": \"$139,575\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Thor Walton\",\n" +
            "      \"position\": \"Developer\",\n" +
            "      \"office\": \"New York\",\n" +
            "      \"number\": \"8327\",\n" +
            "      \"startDate\": \"2013/08/11\",\n" +
            "      \"salary\": \"$98,540\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Finn Camacho\",\n" +
            "      \"position\": \"Support Engineer\",\n" +
            "      \"office\": \"San Francisco\",\n" +
            "      \"number\": \"2927\",\n" +
            "      \"startDate\": \"2009/07/07\",\n" +
            "      \"salary\": \"$87,500\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Serge Baldwin\",\n" +
            "      \"position\": \"Data Coordinator\",\n" +
            "      \"office\": \"Singapore\",\n" +
            "      \"number\": \"8352\",\n" +
            "      \"startDate\": \"2012/04/09\",\n" +
            "      \"salary\": \"$138,575\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Zenaida Frank\",\n" +
            "      \"position\": \"Software Engineer\",\n" +
            "      \"office\": \"New York\",\n" +
            "      \"number\": \"7439\",\n" +
            "      \"startDate\": \"2010/01/04\",\n" +
            "      \"salary\": \"$125,250\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Zorita Serrano\",\n" +
            "      \"position\": \"Software Engineer\",\n" +
            "      \"office\": \"San Francisco\",\n" +
            "      \"number\": \"4389\",\n" +
            "      \"startDate\": \"2012/06/01\",\n" +
            "      \"salary\": \"$115,000\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Jennifer Acosta\",\n" +
            "      \"position\": \"Junior Javascript Developer\",\n" +
            "      \"office\": \"Edinburgh\",\n" +
            "      \"number\": \"3431\",\n" +
            "      \"startDate\": \"2013/02/01\",\n" +
            "      \"salary\": \"$75,650\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Cara Stevens\",\n" +
            "      \"position\": \"Sales Assistant\",\n" +
            "      \"office\": \"New York\",\n" +
            "      \"number\": \"3990\",\n" +
            "      \"startDate\": \"2011/12/06\",\n" +
            "      \"salary\": \"$145,600\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Hermione Butler\",\n" +
            "      \"position\": \"Regional Director\",\n" +
            "      \"office\": \"London\",\n" +
            "      \"number\": \"1016\",\n" +
            "      \"startDate\": \"2011/03/21\",\n" +
            "      \"salary\": \"$356,250\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Lael Greer\",\n" +
            "      \"position\": \"Systems Administrator\",\n" +
            "      \"office\": \"London\",\n" +
            "      \"number\": \"6733\",\n" +
            "      \"startDate\": \"2009/02/27\",\n" +
            "      \"salary\": \"$103,500\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Jonas Alexander\",\n" +
            "      \"position\": \"Developer\",\n" +
            "      \"office\": \"San Francisco\",\n" +
            "      \"number\": \"8196\",\n" +
            "      \"startDate\": \"2010/07/14\",\n" +
            "      \"salary\": \"$86,500\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Shad Decker\",\n" +
            "      \"position\": \"Regional Director\",\n" +
            "      \"office\": \"Edinburgh\",\n" +
            "      \"number\": \"6373\",\n" +
            "      \"startDate\": \"2008/11/13\",\n" +
            "      \"salary\": \"$183,000\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Michael Bruce\",\n" +
            "      \"position\": \"Javascript Developer\",\n" +
            "      \"office\": \"Singapore\",\n" +
            "      \"number\": \"5384\",\n" +
            "      \"startDate\": \"2011/06/27\",\n" +
            "      \"salary\": \"$183,000\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Donna Snider\",\n" +
            "      \"position\": \"Customer Support\",\n" +
            "      \"office\": \"New York\",\n" +
            "      \"number\": \"4226\",\n" +
            "      \"startDate\": \"2011/01/25\",\n" +
            "      \"salary\": \"$112,000\"\n" +
            "    }\n" +
            "]";
    private List<Member> members;

    @PostConstruct
    public void init() throws IOException {
        members = objectMapper.readValue(DATA, new TypeReference<List<Member>>() {});
    }

    public Page<Member> find(final Pageable pageable) throws IOException {
        ArrayList<Member> copied = new ArrayList<>();
        copied.addAll(members);
        copied.sort((member, other) -> {
            ComparisonChain chain = ComparisonChain.start();
            for (Sort.Order order : pageable.getSort()) {
                String property = order.getProperty();
                Ordering<Comparable> ordering = Ordering.natural();
                chain = chain.compare(getAttribute(member, property), getAttribute(other, property), order.getDirection().isAscending() ? ordering : ordering.reverse());
            }

            return chain.result();
        });

        List<Member> result = copied.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());

        return new PageImpl<>(result, pageable, members.size());
    }

    private static Comparable getAttribute(Member member, String attribute) {
        switch (attribute) {
            case "name":
                return member.getName();
            case "position":
                return member.getPosition();
            case "office":
                return member.getOffice();
            case "number":
                return member.getNumber();
            case "startDate":
                return member.getStartDate();
            case "salary":
                return member.getSalary();
            default:
                return null;
        }
    }

    public static class Member {

        private String name;
        private String position;
        private String office;
        private String number;
        private String startDate;
        private String salary;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getOffice() {
            return office;
        }

        public void setOffice(String office) {
            this.office = office;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getSalary() {
            return salary;
        }

        public void setSalary(String salary) {
            this.salary = salary;
        }
    }

}
