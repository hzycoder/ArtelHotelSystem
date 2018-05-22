package com.common.dao;

import java.util.List;

import com.common.dto.AllDataDto;
import com.common.dto.SlotStatusDto;

public interface CommonDao {
	public Integer getCount(String sql);
	public List<?> getOneLine(String sql);
	public List<AllDataDto> export();
	public List<SlotStatusDto> exportSlotStatus(String hotelId);
}
