package ru.javawebinar.topjava.service.jpa;


import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.AbstractMealServiceTest;

import static ru.javawebinar.topjava.Profiles.JPA;

@ActiveProfiles(JPA)
public class MealServiceJpaTest extends AbstractMealServiceTest
{
}
