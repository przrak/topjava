package space.pirs.modules.user.to;

import java.time.LocalDateTime;

public interface TeamSizedWithCoachTo {
    Integer getId();

    String getName();

    int getSize();

    int getCoachId();

    String getCoachName();

    LocalDateTime getRegistered();
}