package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.BodySizeInfo;
import net.fashiongo.webadmin.model.pojo.ColorListInfo;
import net.fashiongo.webadmin.model.pojo.FabricInfo;
import net.fashiongo.webadmin.model.pojo.LengthInfo;
import net.fashiongo.webadmin.model.pojo.PatternInfo;
import net.fashiongo.webadmin.model.pojo.StyleInfo;

/**
 * @author Nayeon Kim
 */
public class GetProductAttributesTotalResponse {
	@JsonProperty("Table")
	private List<PatternInfo> patternInfolist;
	
	@JsonProperty("Table1")
	private List<LengthInfo> lengthInfolist;
	
	@JsonProperty("Table2")
	private List<StyleInfo> styleInfolist;
	
	@JsonProperty("Table3")
	private List<FabricInfo> fabricInfolist;
	
	@JsonProperty("Table4")
	private List<BodySizeInfo> bodySizeInfolist;
	
	@JsonProperty("Table5")
	private List<ColorListInfo> colorListInfolist;

	public List<PatternInfo> getPatternInfolist() {
		return patternInfolist;
	}

	public void setPatternInfolist(List<PatternInfo> patternInfolist) {
		this.patternInfolist = patternInfolist;
	}

	public List<LengthInfo> getLengthInfolist() {
		return lengthInfolist;
	}

	public void setLengthInfolist(List<LengthInfo> lengthInfolist) {
		this.lengthInfolist = lengthInfolist;
	}

	public List<StyleInfo> getStyleInfolist() {
		return styleInfolist;
	}

	public void setStyleInfolist(List<StyleInfo> styleInfolist) {
		this.styleInfolist = styleInfolist;
	}

	public List<FabricInfo> getFabricInfolist() {
		return fabricInfolist;
	}

	public void setFabricInfolist(List<FabricInfo> fabricInfolist) {
		this.fabricInfolist = fabricInfolist;
	}

	public List<BodySizeInfo> getBodySizeInfolist() {
		return bodySizeInfolist;
	}

	public void setBodySizeInfolist(List<BodySizeInfo> bodySizeInfolist) {
		this.bodySizeInfolist = bodySizeInfolist;
	}

	public List<ColorListInfo> getColorListInfolist() {
		return colorListInfolist;
	}

	public void setColorListInfolist(List<ColorListInfo> colorListInfolist) {
		this.colorListInfolist = colorListInfolist;
	}
}
