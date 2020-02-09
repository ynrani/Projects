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
package com.itap.model.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.itap.model.DO.ITAPUserDtlDO;
import com.itap.model.DTO.ITAPUserDtlDTO;
import com.itap.model.mapper.ITAPUserDtlMapper;

@Component
@Service("smartFoundryUserDtlMapper")
public class ITAPUserDtlMapperImpl implements ITAPUserDtlMapper {
	@Override
	public ITAPUserDtlDTO convertFromITAPUserDtlDOToITAPUserDtlDTO(
			ITAPUserDtlDO smartFoundryUserDtlDO,
			ITAPUserDtlDTO smartFoundryUserDtlDTO) {
		try {
			if (StringUtils.isNotEmpty(smartFoundryUserDtlDO.getUserId())) {
				smartFoundryUserDtlDTO.setUserId(smartFoundryUserDtlDO
						.getUserId());
			}
			if (StringUtils.isNotEmpty(smartFoundryUserDtlDO.getEnabled())) {
				smartFoundryUserDtlDTO.setEnabled(smartFoundryUserDtlDO
						.getEnabled());
			}
			if (StringUtils.isNotEmpty(smartFoundryUserDtlDO.getPassWord())) {
				smartFoundryUserDtlDTO.setPassWord(smartFoundryUserDtlDO
						.getPassWord());
			}
			if (StringUtils.isNotEmpty(smartFoundryUserDtlDO.getUserEmail())) {
				smartFoundryUserDtlDTO.setUserEmail(smartFoundryUserDtlDO
						.getUserEmail());
			}
			if (StringUtils.isNotEmpty(smartFoundryUserDtlDO.getUserName())) {
				smartFoundryUserDtlDTO.setUserName(smartFoundryUserDtlDO
						.getUserName());
			}
			if (StringUtils.isNotEmpty(smartFoundryUserDtlDO.getUserPh())) {
				smartFoundryUserDtlDTO.setUserPh(smartFoundryUserDtlDO
						.getUserPh());
			}
			if (StringUtils.isNotEmpty(smartFoundryUserDtlDO.getUserType())) {
				smartFoundryUserDtlDTO.setUserType(smartFoundryUserDtlDO
						.getUserType());
			}

		} catch (Throwable t) {
			t.printStackTrace();
		}

		return smartFoundryUserDtlDTO;
	}

	@Override
	public ITAPUserDtlDO convertFromITAPUserDtlDTOToITAPUserDtlDO(
			ITAPUserDtlDTO smartFoundryUserDtlDTO,
			ITAPUserDtlDO smartFoundryUserDtlDO) {
		try {
			if (StringUtils.isNotEmpty(smartFoundryUserDtlDTO.getUserId())) {
				smartFoundryUserDtlDO.setUserId(smartFoundryUserDtlDTO
						.getUserId());
			}
			if (StringUtils.isNotEmpty(smartFoundryUserDtlDTO.getEnabled())) {
				smartFoundryUserDtlDO.setEnabled(smartFoundryUserDtlDTO
						.getEnabled());
			} else {
				smartFoundryUserDtlDO.setEnabled("1");
			}
			if (StringUtils.isNotEmpty(smartFoundryUserDtlDTO.getPassWord())) {
				smartFoundryUserDtlDO.setPassWord(smartFoundryUserDtlDTO
						.getPassWord());
			}
			if (StringUtils.isNotEmpty(smartFoundryUserDtlDTO.getUserEmail())) {
				smartFoundryUserDtlDO.setUserEmail(smartFoundryUserDtlDTO
						.getUserEmail());
			}
			if (StringUtils.isNotEmpty(smartFoundryUserDtlDTO.getUserName())) {
				smartFoundryUserDtlDO.setUserName(smartFoundryUserDtlDTO
						.getUserName());
			}
			if (StringUtils.isNotEmpty(smartFoundryUserDtlDTO.getUserPh())) {
				smartFoundryUserDtlDO.setUserPh(smartFoundryUserDtlDTO
						.getUserPh());
			}
			if (StringUtils.isNotEmpty(smartFoundryUserDtlDTO.getUserType())) {
				smartFoundryUserDtlDO.setUserType(smartFoundryUserDtlDTO
						.getUserType());
			} else {
				smartFoundryUserDtlDO.setUserType("ROLE_USER");
			}

		} catch (Throwable t) {
			t.printStackTrace();
		}

		return smartFoundryUserDtlDO;
	}

	@Override
	public List<ITAPUserDtlDTO> converITAPUserDtlDOsToITAPUserDtlDTOs(
			List<ITAPUserDtlDO> smartFoundryUserDtlDOs) {

		ITAPUserDtlDTO smartFoundryUserDtlDTO = null;
		List<ITAPUserDtlDTO> smartFoundryUserDtlDTOs = null;
		if (null != smartFoundryUserDtlDOs) {
			smartFoundryUserDtlDTOs = new ArrayList<ITAPUserDtlDTO>();
			for (ITAPUserDtlDO smartFoundryUserDtlDO : smartFoundryUserDtlDOs) {
				smartFoundryUserDtlDTO = new ITAPUserDtlDTO();
				smartFoundryUserDtlDTO = convertFromITAPUserDtlDOToITAPUserDtlDTO(
						smartFoundryUserDtlDO, smartFoundryUserDtlDTO);
				smartFoundryUserDtlDTOs.add(smartFoundryUserDtlDTO);
			}

		}
		return smartFoundryUserDtlDTOs;
	}
}
