package net.engineeringdigest.journalApp.scheduler;

import net.engineeringdigest.journalApp.cache.AppCache;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepositoryImpl;
import net.engineeringdigest.journalApp.service.EmailService;
import net.engineeringdigest.journalApp.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

@Autowired
private EmailService emailService;

@Autowired
private UserRepositoryImpl userRepository;

@Autowired
private SentimentAnalysisService sentimentAnalysisService;

@Autowired
private AppCache appCache;

//    @Scheduled(cron = "0 * * ? * *")
@Scheduled(cron = "0 0 9 * * SUN")
public void fetchUsersAndSendMail(){
    List<User> users = userRepository.getUserForSA();
    System.out.println("Scheduler Started");
    System.out.println("Total Users Found : " + users.size());
    for (User user:users){
        System.out.println("Processing user : " + user.getUserName());
        List<JournalEntry> journalEntries = user.getJournalEntries();
        List<String> filteredEntries = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getContent()).collect(Collectors.toList());
        String entry = String.join(" ",filteredEntries);
        String sentiment = sentimentAnalysisService.getSentiment(entry);
        System.out.println("Sentiment : " + sentiment);
        emailService.sendEmail(user.getEmail(),"Sentiments for last 7 days", sentiment);
    }
}

    @Scheduled(cron = "*/10 * * * * *")
public void clearAppCache(){
   appCache.init();
}

}
