package WebApp.Controllers;

import WebApp.DataBase.Entities.DataBaseHit;
import WebApp.DataBase.Services.HitService;
import WebApp.Functional.*;
import WebApp.Utils.HitDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = {"api/hits"})
public class MainController {
    private final HitService hitService;

    public MainController(@Autowired HitService hitService) {
        this.hitService = hitService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<HitDTO>> addNewHit(@RequestBody DataHit dataHit, HttpServletRequest request) {
        NumberConditionalContainer xContainer = new NumberConditionalContainer(dataHit.getX());
        NumberConditionalContainer yContainer = new NumberConditionalContainer(dataHit.getY());
        NumberConditionalContainer rContainer = new NumberConditionalContainer(dataHit.getR());

        TextFieldConditionalContainer xField = new TextFieldConditionalContainer(xContainer, -3, 5);
        TextFieldConditionalContainer yField = new TextFieldConditionalContainer(yContainer, -5, 3);
        TextFieldConditionalContainer rField = new TextFieldConditionalContainer(rContainer, 0, 5);

        CachedConditionalContainer<Float> xCached = new CachedConditionalContainer<>(xField);
        CachedConditionalContainer<Float> yCached = new CachedConditionalContainer<>(yField);
        CachedConditionalContainer<Float> rCached = new CachedConditionalContainer<>(rField);

        Hit hit = new Hit(xCached, yCached, rCached);
        CachedResult cachedResult = new CachedResult(hit);
        Timer timer = new Timer(cachedResult);

        try {
            hitService.addHit(new DataBaseHit(dataHit.getR(), dataHit.getX(), dataHit.getY(),
                    hit.result(), ZonedDateTime.now(), timer.time(), (String) request.getAttribute("username")));

            return new ResponseEntity<>(hitService.getAll((String) request.getAttribute("username"))
                    .stream().map(HitDTO::new).collect(Collectors.toList()), HttpStatus.OK);
        } catch (WrongInputException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Получены некорректные данные.");
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<HitDTO>> getHits(HttpServletRequest request) {
        return new ResponseEntity<>(hitService.getAll((String) request.getAttribute("username"))
                .stream().map(HitDTO::new).collect(Collectors.toList()), HttpStatus.OK);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class DataHit {
        private String r;
        private String x;
        private String y;
    }
}
