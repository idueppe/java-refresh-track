package de.crowdcode.vehicle.fleet.dao;

import java.util.List;

import de.crowdcode.vehicle.dao.EntityDao;
import de.crowdcode.vehicle.fleet.domain.EngineInfo;
import de.crowdcode.vehicle.fleet.domain.Fleet;

public interface FleetDao extends EntityDao<Fleet> {

    public Fleet findByCompanyName(String company);

    public List<String> findAllCompanyNames();

    public List<EngineInfo> getEngineReport(String companyName);

}
