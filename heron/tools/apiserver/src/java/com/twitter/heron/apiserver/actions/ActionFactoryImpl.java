//  Copyright 2017 Twitter. All rights reserved.
//
//  Licensed under the Apache License, Version 2.0 (the "License");
//  you may not use this file except in compliance with the License.
//  You may obtain a copy of the License at
//
//  http://www.apache.org/licenses/LICENSE-2.0
//
//  Unless required by applicable law or agreed to in writing, software
//  distributed under the License is distributed on an "AS IS" BASIS,
//  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  See the License for the specific language governing permissions and
//  limitations under the License.
package com.twitter.heron.apiserver.actions;

import com.twitter.heron.api.exception.InvalidTopologyException;
import com.twitter.heron.api.generated.TopologyAPI;
import com.twitter.heron.api.utils.TopologyUtils;
import com.twitter.heron.apiserver.utils.ConfigUtils;
import com.twitter.heron.spi.common.Config;

public class ActionFactoryImpl implements ActionFactory {

  @Override
  public Action createSubmitAction(Config config, String topologyPackagePath,
        String topologyBinaryFileName, String topologyDefinitionPath) {
    final TopologyAPI.Topology topology;
    try {
      topology = TopologyUtils.getTopology(topologyDefinitionPath);
    } catch (InvalidTopologyException e) {
      throw new RuntimeException(e);
    }
    final Config topologyConfig =
        ConfigUtils.getTopologyConfig(topologyPackagePath, topologyBinaryFileName,
            topologyDefinitionPath, topology);
    final Config configWithTopology =
        Config.newBuilder()
            .putAll(config)
            .putAll(topologyConfig)
            .build();

    return new SubmitTopologyAction(configWithTopology, topology);
  }

  @Override
  public Action createRuntimeAction(Config config, ActionType type) {
    return new TopologyRuntimeAction(config, type.command);
  }
}
