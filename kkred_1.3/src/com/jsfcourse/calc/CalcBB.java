package com.jsfcourse.calc;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


@Named
@RequestScoped
//@SessionScoped
public class CalcBB {
	private String kwota;
	private String oproc;
	private String raty;
	private Double result;
	private Double years;
	private Double q;

	@Inject
	FacesContext ctx;

	

	public String getKwota() {
		return kwota;
	}

	public void setKwota(String kwota) {
		this.kwota = kwota;
	}

	public String getOproc() {
		return oproc;
	}

	public void setOproc(String oproc) {
		this.oproc = oproc;
	}
	
	public String getRaty() {
		return raty;
	}

	public void setRaty(String raty) {
		this.raty = raty;
	}

	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}

	public boolean doTheMath() {
		try {
			double kwota = Double.parseDouble(this.kwota);
			double oproc = Double.parseDouble(this.oproc);
			double raty = Double.parseDouble(this.raty);
			
			q = 1+((oproc/100)/12);
			years = raty * 12;
			
			result = kwota * Math.pow(q, years) * (q-1)/((Math.pow(q, years))-1);
			result = (double) Math.round(result);
			
//			result = ((kwota*(oproc/100)*raty)/(raty*12)) ;
//			Math.round(result);
//			result/=100;

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			return true;
		} catch (Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów", null));
			return false;
		}
	}

	// Go to "showresult" if ok
	public String calc() {
		if (doTheMath()) {
			return "showresult";
		}
		return null;
	}

	// Put result in messages on AJAX call
	public String calc_AJAX() {
		if (doTheMath()) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Wynik: " + result, null));
		}
		return null;
	}

	public String info() {
		return "info";
	}
}
