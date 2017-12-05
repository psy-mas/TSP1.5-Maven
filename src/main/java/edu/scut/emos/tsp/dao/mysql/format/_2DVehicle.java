package edu.scut.emos.tsp.dao.mysql.format;

import edu.scut.emos.tsp.model.DVehicle;
import edu.scut.emos.tsp.model.Vehicle;

/**
 * @author emos
 */
public class _2DVehicle {

	public static DVehicle a(Vehicle vehicle) {
		DVehicle dvehicle = new DVehicle();
		dvehicle.setVehicleid(vehicle.getId());
		dvehicle.setLength(vehicle.getLength());
		dvehicle.setWidth(vehicle.getWidth());
		dvehicle.setHeight(vehicle.getHeight());
		dvehicle.setTypeofvehicle(vehicle.getTypeOfVehicle());
		dvehicle.setMaxweight(vehicle.getMaxWeight());
		dvehicle.setMaxvolume(vehicle.getMaxVolume());
		dvehicle.setLatestlongitude(vehicle.getPosition().getLongitude());
		dvehicle.setLatestlatitude(vehicle.getPosition().getLatitude());
		dvehicle.setGpsuploadtime(vehicle.getGpsUpdateTime());
		dvehicle.setFuelconsumption(vehicle.getFuelConsumption());
		dvehicle.setLoadedweight(vehicle.getLoadedWeight());
		dvehicle.setLoadedvolume(vehicle.getLoadedVolume());
		dvehicle.setIslocked((byte) (vehicle.isLock() ? 1 : 0));
		dvehicle.setLastupdatetime(vehicle.getLastUpdateTime());
		
		return dvehicle;
	}
}
