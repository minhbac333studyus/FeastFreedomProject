package com.minhle.test;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface MusicRespository extends CrudRepository {
 
	List<Music> findByArtist(String artist);
 
	List<Music> findBySongTitle(String songTitle);
 
	@Query(fields = "artist, songTitle")
	// Note : If projections are used on Global Secondary Indexes, the index must contain the desired fields in the first place
	List<Music> findByYear(Integer year);
 
	List<Music> findByQuality(String quality);
 
	List<Music> findByGenre(String genre);
 
// Note: Order by can be done on one of the attributes of the same index. For example, we wouldn't be able to order by 'year' when finding by artist
	// because our index only contains 'artist' and 'songTitle'
	List<Music> findByArtistOrderBySongTitleDesc(String artist);
 
}