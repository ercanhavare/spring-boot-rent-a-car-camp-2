package kodlama.io.rentACar.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.rentACar.business.abstracts.BrandService;
import kodlama.io.rentACar.business.requests.CreateBrandRequest;
import kodlama.io.rentACar.business.responses.GetAllBrandResponse;
import kodlama.io.rentACar.dataAccess.abstracts.BrandRepository;
import kodlama.io.rentACar.entities.concretes.Brand;

@Service // bu siinif business nesnesidir
public class BrandManager implements BrandService{

	private BrandRepository brandRepository;
		
	@Autowired
	public BrandManager(BrandRepository brandRepository) {
		//super();
		this.brandRepository = brandRepository;
	}

	@Override
	public List<GetAllBrandResponse> getAll() {
		
		List<Brand> brands = brandRepository.findAll();
		List<GetAllBrandResponse> brandResponse = new ArrayList<GetAllBrandResponse>();
		
		for(Brand brand:brands) {
			GetAllBrandResponse responseItem = new GetAllBrandResponse();
			responseItem.setId(brand.getId());
			responseItem.setName(brand.getName());
			
			brandResponse.add(responseItem);
		}
		
		//is kurallari
		return brandResponse;
	}

	@Override
	public void add(CreateBrandRequest createBrandRequest) {
		Brand brand = new Brand();
		brand.setName(createBrandRequest.getName());
		
		this.brandRepository.save(brand);
	}
	
}
