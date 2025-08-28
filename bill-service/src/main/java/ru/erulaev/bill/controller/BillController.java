package ru.erulaev.bill.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.erulaev.bill.dto.BillRequestDTO;
import ru.erulaev.bill.dto.BillResponseDTO;
import ru.erulaev.bill.service.BIllService;

@RestController
@AllArgsConstructor
public class BillController {

    private final BIllService bIllService;

    @GetMapping("/{id}")
    public BillResponseDTO get(@PathVariable long id) {
        return new BillResponseDTO(bIllService.get(id));
    }

    @PostMapping("/")
    public Long create(@RequestBody BillRequestDTO billRequestDTO) {
        return bIllService.create(billRequestDTO.getAccountId(), billRequestDTO.getAmount(),
                billRequestDTO.isDefaulted(), billRequestDTO.isOverdraftEnabled());
    }

    @PutMapping("/{id}")
    public BillResponseDTO update(@PathVariable long id, @RequestBody BillRequestDTO billRequestDTO) {
        return new BillResponseDTO(bIllService.update(id, billRequestDTO.getAccountId(), billRequestDTO.getAmount(),
                billRequestDTO.isDefaulted(), billRequestDTO.isOverdraftEnabled()));
    }

    @DeleteMapping("/{id}")
    public BillResponseDTO delete(@PathVariable long id) {
        return new BillResponseDTO(bIllService.delete(id));
    }
}
