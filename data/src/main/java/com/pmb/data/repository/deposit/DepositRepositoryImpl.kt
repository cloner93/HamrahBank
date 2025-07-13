
class DepositRepositoryImpl @Inject constructor(
    private val client: NetworkManger
) : DepositRepository {
    override fun getDepositList(): Flow<Result<List<DepositModel>>> {
        return client.request<LoginRequest, List<Deposit>>(endpoint = "account/getUserAccounts")
            .mapApiResult {
                it.second.toDomain()
            }
    }
}

private fun List<Deposit>.toDomain(): List<DepositModel> {
    val listOfDeposit = mutableListOf<DepositModel>()
    this.forEach {
        listOfDeposit.add(
            DepositModel(
                title = it.accountTypeDescription ?: "N/A",
                desc = it.organizationName,
                categoryCode = it.categoryCode,
                depositNumber = it.accountNumber.toString(),
                amount = it.balance.toDouble(),
                currency = "ریال",
                ibanNumber = it.shebaNo.toString(),
                cardNumber = ""
            )
        )
    }

    return listOfDeposit
}