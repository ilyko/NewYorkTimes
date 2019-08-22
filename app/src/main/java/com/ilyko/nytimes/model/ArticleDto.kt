package com.ilyko.nytimes.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ilyko.nytimes.Constants
import java.io.Serializable

@Entity(tableName = Constants.TABLE_NAME)
data class ArticleDto(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Long = 0,
    var title: String = "",
    var imageUrlThumb: String = "",
    var imageUrl: String = "",
    var abstract: String = "",
    var publishedDate: String = "",
    var byline: String = "",
    var source: String = ""
) : Serializable {
}
