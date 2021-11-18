package ru.sbt;


import java.util.Collection;

public interface Garage {

    Collection<Owner> allCarsUniqueOwners();
    /**2
     * Complexity should be less than O(n)
     TreeSet ���� ����� �� ��������
     */
    Collection<Car> topThreeCarsByMaxVelocity();

    /**3
     * Complexity should be O(1)
     HashMap, ����: Brand, value: ArrList �����
     */
    Collection<Car> allCarsOfBrand(String brand);

    /**4
     * Complexity should be less than O(n)
     * TreeSet
     */
    Collection<Car> carsWithPowerMoreThan(int power);

    /**5
     * Complexity should be O(1)
     * HashMap, ����: Owner, value: ArrList �����
     */
    Collection<Car> allCarsOfOwner(Owner owner);

    /**6
     * @return mean value of owner age that has cars with given brand
     */
    int meanOwnersAgeOfCarBrand(String brand);

    /**7
     * @return mean value of cars for all owners
     */
    int meanCarNumberForEachOwner();

    /**8
     * Complexity should be less than O(n)
     * @return removed car
    HashMap
     */
    Car removeCar(int carId);

    /**9
     * Complexity should be less than O(n)
     * HashMap
     */
    void addNewCar(Car car, Owner owner);
}
