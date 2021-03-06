/*
 * Copyright 2017 HugeGraph Authors
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.baidu.hugegraph.entity.schema;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabelUpdateEntity implements Typifiable, Stylefiable {

    @JsonProperty("append_properties")
    private Set<Property> appendProperties;

    @JsonProperty("append_property_indexes")
    private List<PropertyIndex> appendPropertyIndexes;

    @JsonProperty("remove_property_indexes")
    private List<String> removePropertyIndexes;

    @JsonProperty("style")
    private SchemaStyle style;

    private transient String name;
    private transient SchemaType type;

    @Override
    public SchemaType getSchemaType() {
        return this.type;
    }

    public List<String> getAppendPropertyIndexNames() {
        if (this.appendPropertyIndexes == null) {
            return Collections.emptyList();
        }
        return this.appendPropertyIndexes.stream()
                                         .map(PropertyIndex::getName)
                                         .collect(Collectors.toList());
    }
}
