/*---------------------------------------------------------------------------------------
 * Object Name: ITAPUserDtlMapper.Java
 * 
 * Modification Block:
 * --------------------------------------------------------------------------------------
 * S.No. Name                Date      Bug Fix no. Desc
 * --------------------------------------------------------------------------------------
 * 1     Seshadri Chowdary          26/02/16  NA          Created
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2016 
 *---------------------------------------------------------------------------------------*/
package com.itap.model.mapper;

import java.util.List;

import com.itap.model.DO.ITAPUserDtlDO;
import com.itap.model.DTO.ITAPUserDtlDTO;

public interface ITAPUserDtlMapper
{
	public ITAPUserDtlDTO convertFromITAPUserDtlDOToITAPUserDtlDTO(ITAPUserDtlDO smartFoundryUserDtlDO,
			ITAPUserDtlDTO smartFoundryUserDtlDTO);

	public ITAPUserDtlDO convertFromITAPUserDtlDTOToITAPUserDtlDO(ITAPUserDtlDTO smartFoundryUserDtlDTO,
			ITAPUserDtlDO smartFoundryUserDtlDO);

	public List<ITAPUserDtlDTO> converITAPUserDtlDOsToITAPUserDtlDTOs(List<ITAPUserDtlDO> smartFoundryUserDtlDO);


}
