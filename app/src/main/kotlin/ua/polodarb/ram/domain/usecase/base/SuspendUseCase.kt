package ua.polodarb.ram.domain.usecase.base

/**
 * Suspend Use Case Interface
 */
interface SuspendUseCase<Input, Output> {
    /**
     * Suspend Use Case invoke
     */
    suspend fun invoke(input: Input): Output
}