package com.draja.network

class NetworkManager {

    companion object {
        @Volatile
        private var instance: NetworkManager? = null

        fun getInstance() = instance

        fun createInstance() {
            instance ?: synchronized(this) {
                instance ?: NetworkManager().also {
                    instance = it
                }
            }
        }

        internal fun destroy() {
            instance = null
        }
    }

    fun <T> createApi(apiClass: Class<T>): T {
        val client = NetworkClient.getNetworkClientInstance()
        return client.create(apiClass)
    }
}