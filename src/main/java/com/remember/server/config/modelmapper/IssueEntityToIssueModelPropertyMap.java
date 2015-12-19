package com.remember.server.config.modelmapper;

import com.github.jmnarloch.spring.boot.modelmapper.PropertyMapConfigurerSupport;
import com.remember.server.entity.IssueEntity;
import com.remember.server.entity.TagEntity;
import com.remember.server.model.NewIssueModel;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by NerdHerd on 2015. 12. 20..
 */

@Configuration
public class IssueEntityToIssueModelPropertyMap extends PropertyMapConfigurerSupport<IssueEntity, NewIssueModel> {

	private Converter<List<TagEntity>, List<String>> tagEntitiesConverter = new AbstractConverter<List<TagEntity>, List<String>>() {
		@Override
		protected List<String> convert(List<TagEntity> source) {
			if (source == null)
				return Collections.emptyList();
			return source.stream().map(TagEntity::getName).collect(Collectors.toList());
		}
	};

	@Override
	public PropertyMap<IssueEntity, NewIssueModel> mapping() {
		return new PropertyMap<IssueEntity, NewIssueModel>() {
			@Override
			protected void configure() {
				map().setContent(source.getContent());
				map().setCreatedAt(source.getCreatedAt());
				map().setTitle(source.getTitle());
				using(tagEntitiesConverter).map(source.getTags()).setTags(null);
			}
		};
	}
}
