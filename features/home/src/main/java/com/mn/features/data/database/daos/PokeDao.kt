package com.mn.features.data.database.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.mn.features.data.database.entities.PokeEntity

@Dao
interface PokeDao {

    @Upsert
    fun insertAll(pokeList: List<PokeEntity>)

    @Query("Select * from PokeEntity")
    fun pagingSource(): PagingSource<Int, PokeEntity>

    @Query("Delete from PokeEntity")
    fun clearAll()

}