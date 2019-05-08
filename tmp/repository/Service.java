@Transactional
public List<CoachTo> getAll(){
        List<TeamSizedWithCoachTo> teamsWithSize=teamRepository.getTeamsWithSize();
        Map<CoachTo, List<TeamSizedTo>>coachesMap=teamsWithSize.stream()
        .collect(Collectors.groupingBy(
        ts->new CoachTo(ts.getCoachId(),ts.getCoachName(),ts.getRegistered()),
        filtering(ts->ts.getId()!=null,
        mapping(ts->new TeamSizedTo(ts.getId(),ts.getName(),ts.getSize()),toList()))));
        coachesMap.forEach(CoachTo::setTeams);
        List<CoachTo> coaches=new ArrayList<>(coachesMap.keySet());
        coaches.sort(Comparator.comparing(CoachTo::getName));
        return coaches;
        }