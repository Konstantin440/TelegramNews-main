package org.example.telegramnews.service;

import lombok.RequiredArgsConstructor;
import org.example.telegramnews.api2.model.OpenAiService;
import org.example.telegramnews.client.NewsClient;
import org.example.telegramnews.entity.Article;
import org.example.telegramnews.entity.NewsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class NewsClientServiceImpl {
    private final NewsClient newsClient;
    private final Set<String> sendNewsTitles = new HashSet<>();


    public Optional<Article> getLatestJavaNews() {


        NewsResponse response = newsClient.getNewsResponse(LocalDate.now().minusDays(1), "JAVA");
        List<Article> articles = response.getArticles();

        if (articles != null && !articles.isEmpty()) {
            for (Article article : articles) {
                String title = article.getTitle();
                if (!sendNewsTitles.contains(title)) {
                    sendNewsTitles.add(title);
                    return Optional.of(article); // Нашли новую
                }
            }
        }

        return Optional.empty();
    }


    public String formatNewsMessage(Article article) {
        StringBuilder message = new StringBuilder();
        String substringData = article.getPublishedAt().substring(0, 10);

        message.append("🗞️ <b>")
                .append(article.getTitle())
                .append("</b>\n\n")
                .append("📝 <b>Краткое описание:</b>\n")
                .append(article.getDescription())
                .append(" <a href=\"").append(article.getUrl()).append("\">🔸 Читать полностью</a>\n\n")

                .append("🕒 <i>Дата публикации:</i> ")
                .append(substringData)
                .append("\n")
                .append("👨‍💻 <i>Автор:</i> ")
                .append(article.getAuthor());

        return message.toString();
    }


}
