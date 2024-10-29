package ua.polodarb.ram.domain.usecase.base

/**
 * Use Case Interface
 */
interface UseCase<Input, Output> {
    /**
     * Use Case invoke
     */
    fun invoke(input: Input): Output
}