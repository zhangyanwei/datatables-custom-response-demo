package demo.api;

import demo.service.DataService;
import demo.service.DataService.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path = "/api")
public class DataApi {

    @Autowired
    private DataService dataService;

    @GetMapping("/members")
    public Paged<Member> search(@PageableDefault Pageable pageable) throws IOException {
        Page<Member> members = dataService.find(pageable);
        Paged<Member> paged = new Paged<>();
        paged.setContent(members.getContent());
        paged.setTotal(members.getTotalElements());
        paged.setSize(members.getSize());
        paged.setPage(members.getNumber());
        paged.setLast(members.isLast());
        return paged;
    }

}
