package Cloud_vendor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Cloud_vendor.entity.cloud_vendor;

@Repository
public interface Cloud_vendorRepo extends JpaRepository<cloud_vendor, Integer> {
	public cloud_vendor findByEmail(String email);

}
