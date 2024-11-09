package ua.polodarb.ram.data.repository.models.base

import ua.polodarb.ram.data.network.model.base.BaseNetworkModel

interface BaseRepositoryNetworkMapper<Repository : BaseRepoModel, Network : BaseNetworkModel> {

    fun Network.toRepository(): Repository

    fun Repository.toNetwork(): Network

}
