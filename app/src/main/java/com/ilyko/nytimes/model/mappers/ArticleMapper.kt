package com.ilyko.nytimes.model.mappers

import com.ilyko.nytimes.model.Article
import com.ilyko.nytimes.model.ArticleDto
import javax.inject.Inject


class ArticleMapper
@Inject
constructor() : Mapper<ArticleDto, Article> {
    override fun map(input: Article): ArticleDto {
        return ArticleDto(
            input.id,
            input.title,
            input.media[0].mediaMetadata[0].url,
            input.media[0].mediaMetadata[1].url,
            input.abstract,
            input.published_date,
            input.byline,
            input.source
        )
    }
}