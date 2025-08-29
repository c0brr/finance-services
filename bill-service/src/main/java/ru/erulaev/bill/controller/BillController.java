package ru.erulaev.bill.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.erulaev.bill.dto.BillRequestDTO;
import ru.erulaev.bill.dto.BillResponseDTO;
import ru.erulaev.bill.service.BIllService;

import java.util.List;
import java.util.stream.Collectors;

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
                billRequestDTO.isDefault(), billRequestDTO.isOverdraftEnabled());
    }

    @PutMapping("/{id}")
    public BillResponseDTO update(@PathVariable long id, @RequestBody BillRequestDTO billRequestDTO) {
        return new BillResponseDTO(bIllService.update(id, billRequestDTO.getAccountId(), billRequestDTO.getAmount(),
                billRequestDTO.isDefault(), billRequestDTO.isOverdraftEnabled()));
    }

    @DeleteMapping("/{id}")
    public BillResponseDTO delete(@PathVariable long id) {
        return new BillResponseDTO(bIllService.delete(id));
    }

    @GetMapping("/account/{accountId}")
    public List<BillResponseDTO> getBillsByAccountId(@PathVariable long accountId) {
        return bIllService.getBillsByAccountId(accountId).stream()
                .map(BillResponseDTO::new)
                .collect(Collectors.toList());
    }
}
