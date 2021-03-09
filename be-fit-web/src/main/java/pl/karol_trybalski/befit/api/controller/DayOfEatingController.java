package pl.karol_trybalski.befit.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.karol_trybalski.befit.dto.dto.day_of_eating.DayOfEatingBeginDTO;
import pl.karol_trybalski.befit.dto.dto.day_of_eating.DayOfEatingDTO;
import pl.karol_trybalski.befit.dto.dto.day_of_eating.DayOfEatingLiteDTO;
import pl.karol_trybalski.befit.dto.mapper.DayOfEatingMapper;
import pl.karol_trybalski.befit.service.DayOfEatingServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping(path = "/days-of-eating")
@RestController
public class DayOfEatingController {

    private final DayOfEatingServiceImpl service;

    @GetMapping("lite")
    public List<DayOfEatingLiteDTO> findAll() {
        return service.findAllLites().stream().map(DayOfEatingMapper.INSTANCE::map).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public DayOfEatingDTO get(final @PathVariable Long id) {
        return DayOfEatingMapper.INSTANCE.map(service.getOne(id));
    }

    @PostMapping
    public Long create(@RequestBody DayOfEatingBeginDTO begin) {
        return service.create(DayOfEatingMapper.INSTANCE.map(begin));
    }

    @GetMapping("can-begin-day-of-eating")
    public boolean canBeginDayOfEating() {
        return service.canBeginDayOfEating();
    }

//    @PutMapping("/{id}")
//    public MealDTO create(final @PathVariable Long id, final @RequestBody MealCUDTO meal) {
//        return MealMapper.INSTANCE.map(mealService.update(id, meal));
//    }
}
