public interface BaseMapper<E extends AbstractBaseEntity, T extends BaseTo> {

    default T toToWithCheck(E entity, int id) {
        return toTo(checkFoundById(entity, id));
    }

    T toTo(E entity);

    List<T> toToList(Collection<E> entities);

    E toEntity(T to);

    List<E> toEntityList(Collection<T> tos);

    default LocalDate asLocalDate(LocalDateTime ldt) {
        return ldt.toLocalDate();
    }
}