package io.milvus.rg;

import java.util.List;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ResourceGroupInfo {
    private String resourceGroupName;
    private List<String> fullDatabases; // databases belong to this resource group completely.
    private List<String> partialDatabases; // databases belong to this resource group partially, some collection is in other resource group.
    private Integer availableNodeNum; // actual query node in this resource group.
    private Integer requestsNodeNum; // max query node in this resource group.

    /**
     * Check if this resource group is the default resource group.
     * 
     * @return true if this resource group is the default resource group.
     */
    public boolean isDefaultResourceGroup() {
        return this.resourceGroupName == ResourceGroupManagement.DEFAULT_RG;
    }

    /**
     * Check if this resource group is the recycle resource group.
     * 
     * @return true if this resource group is the recycle resource group.
     */
    public boolean isRecycleResourceGroup() {
        return this.resourceGroupName == ResourceGroupManagement.RECYCLE_RG;
    }
}
